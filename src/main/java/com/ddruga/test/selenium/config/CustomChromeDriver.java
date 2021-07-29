package com.ddruga.test.selenium.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomChromeDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomChromeDriver.class);

    public CustomChromeDriver() {
    }

    public static WebDriver getInstance() {
        return createWebDriver();
    }

    private static WebDriver createWebDriver() {
        ChromeOptions options = setOptions();
        ChromeDriverService driverService = setDriverService();
        return new ChromeDriver(driverService, options);
    }

    private static ChromeOptions setOptions() {
        ChromeOptions options = new ChromeOptions();

        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized");

        // This removes the password popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    private static ChromeDriverService setDriverService() {
        DriverService.Builder<?, ?> serviceBuilder = new ChromeDriverService.Builder().withSilent(true);
        ChromeDriverService chromeDriverService = (ChromeDriverService) serviceBuilder.build();
        try {
            chromeDriverService.sendOutputTo(new FileOutputStream("nul"));
        } catch (FileNotFoundException e) {
            LOGGER.error("Could not redirect loggging to nul file", e);
        }
        return chromeDriverService;
    }
}
