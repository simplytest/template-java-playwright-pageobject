package de.simplytest.tests;

import de.simplytest.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestCase extends BaseTest {
    @Test
    public void verifyHeadingIsVisible() {
        assertThat(homepage.getHeading1()).isVisible();
    }
}
