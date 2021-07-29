package com.ddruga.test.selenium.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEdgeDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEdgeDriver.class);
    private static WebDriver edgeDriverInstance = null;

    private CustomEdgeDriver() {
    }

    public static WebDriver getInstance() {

        if (edgeDriverInstance == null) {
            return createWebDriver();
        }

        return edgeDriverInstance;
    }

    private static WebDriver createWebDriver() {
        EdgeOptions options = setOptions();
        EdgeDriverService driverService = setDriverService();
        return new EdgeDriver(driverService, options);
    }

    private static EdgeOptions setOptions() {
        EdgeOptions options = new EdgeOptions();

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

    private static EdgeDriverService setDriverService() {
        DriverService.Builder<?, ?> serviceBuilder = new EdgeDriverService.Builder().withSilent(true);
        EdgeDriverService edgeDriverService = (EdgeDriverService) serviceBuilder.build();
        try {
            edgeDriverService.sendOutputTo(new FileOutputStream("nul"));
        } catch (FileNotFoundException e) {
            LOGGER.error("Could not redirect loggging to nul file", e);
        }
        return edgeDriverService;
    }
}
