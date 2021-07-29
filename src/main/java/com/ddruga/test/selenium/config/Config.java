package com.ddruga.test.selenium.config;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class Config {

    private Config() {
    }

    public static DriverManagerType getBrowserType() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }

        DriverManagerType driverType;
        if (browser.equalsIgnoreCase("chrome")) {
            driverType = DriverManagerType.CHROME;
        } else if (browser.equalsIgnoreCase("edge")) {
            driverType = DriverManagerType.EDGE;
        } else {
            throw new IllegalArgumentException("Browser type not recognized");
        }
        return driverType;
    }
}
