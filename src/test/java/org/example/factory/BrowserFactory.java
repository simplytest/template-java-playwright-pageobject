package org.example.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserFactory {
    private Playwright playwright;
    private Browser browser;
    private Properties properties;

    public Page initializeBrowser(String browserName, String headless) throws IllegalArgumentException {
        boolean isHeadless = Boolean.parseBoolean(headless);

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(isHeadless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                break;
            default:
                throw new IllegalArgumentException("Please provide a valid browser name (chrome, firefox, webkit or chromium).");
        }

        BrowserContext browserContext = browser.newContext();

        return browserContext.newPage();
    }

    public Properties initializeConfigProperties() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String configUrl = classLoader.getResource("config/config.properties").getPath();
            FileInputStream fileInputStream = new FileInputStream(configUrl);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
