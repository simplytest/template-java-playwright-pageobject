package org.example.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

public class HomepageTest extends BaseTest {
    @Test
    public void verifyHeadingIsVisible() {
        PlaywrightAssertions.assertThat(homepage.getHeading1()).isVisible();
    }
}
