package com.tfl.api.test;

import com.frameworkium.core.api.tests.BaseTest;
import com.google.common.collect.ImmutableMap;
import com.tfl.api.services.journeyPlanner.JourneyPlannerDisambiguationResponse;
import com.tfl.api.services.journeyPlanner.JourneyPlannerItineraryResponse;
import org.testng.annotations.Test;
import com.tfl.api.services.journeyPlanner.JourneyPlannerService;

import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class JourneyPlannerTest extends BaseTest {

    @Test
    public void journey_planner_london_search_journey_duration() {

        JourneyPlannerDisambiguationResponse disambiguationResponse =
                JourneyPlannerService.newInstance(
                        "Blue Fin Building, Southwark",
                        "Waterloo Station, London",
                        JourneyPlannerDisambiguationResponse.class);

        String from = disambiguationResponse.getFrom();
        String to = disambiguationResponse.getFirstDisambiguatedTo();

        int shortestJourneyDuration = JourneyPlannerService
                .newInstance(from, to, JourneyPlannerItineraryResponse.class)
                .getShortestJourneyDuration();

        assertThat(shortestJourneyDuration).isLessThan(30);
    }

    @Test
    public void journey_planner_national_search_journey_duration() {

        Map<String, String> params = ImmutableMap.of("nationalSearch", "True");

        JourneyPlannerItineraryResponse response =
                JourneyPlannerService.newInstance(
                        "Blue Fin Building, Southwark",
                        "Surrey Research Park, Guildford",
                        params,
                        JourneyPlannerItineraryResponse.class);

        int shortestJourneyDuration = response.getShortestJourneyDuration();

        assertThat(shortestJourneyDuration).isGreaterThan(45);
    }

}
