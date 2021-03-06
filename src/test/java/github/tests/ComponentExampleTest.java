package github.tests;

import com.frameworkium.core.ui.tests.BaseUITest;
import github.pages.HomePage;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

public class ComponentExampleTest extends BaseUITest {

    // disabled due to lack of time for maintenance
    // still useful as an example
    @TmsLink("CET-1")
    @Test(enabled = false)
    public final void componentExampleTest() {

        // Navigate to homepage then use the nav bar to go to the explore page
        var explorePage = HomePage.open().then().with().theHeader().clickExplore();

        // not a good assertion, improving this is an exercise for the reader
        assertThat(explorePage.getTitle()).isEqualTo("Explore · GitHub");

        // Search for "Selenium" and check SeleniumHQ/selenium is one of the returned repos.
        var searchResults = explorePage.with().theHeader()
                .search("Selenium")
                .getRepoNames();
        assertThat(searchResults).contains("SeleniumHQ/selenium");
    }
}
