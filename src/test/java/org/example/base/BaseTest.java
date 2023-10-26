package org.example.base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.example.factory.BrowserFactory;
import org.example.pages.HomePage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest {
    private final BrowserFactory browserFactory = new BrowserFactory();
    protected Properties properties = browserFactory.initializeConfigProperties();
    protected Page page;
    protected HomePage homepage;
    private String browserName;

    @BeforeMethod
    @Parameters({"browserName", "headless"})
    public void setUp(@Optional("chrome") String browserName, @Optional("false") String headless) throws IllegalArgumentException {
        this.browserName = browserName;

        page = browserFactory.initializeBrowser(browserName, headless);

        page.navigate(properties.getProperty("BASE_URL").trim());

        homepage = new HomePage(page);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = formatter.format(currentDateTime);

            String resultName = result.getName() + "_" + browserName + "_" + formattedDateTime;
            Path screenshotPath = Paths.get("./target/screenshots/" + resultName + ".png");

            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
            attachScreenshotToAllureReport(resultName, screenshotPath);

        }

        page.context().browser().close();
    }

    @Attachment(type = "image/png")
    private void attachScreenshotToAllureReport(String resultName, Path screenshotPath) throws IOException {
        Allure.addAttachment(resultName, new ByteArrayInputStream(Files.readAllBytes(screenshotPath)));
    }
}
