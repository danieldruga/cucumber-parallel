package com.ddruga.test.selenium.config;

import org.springframework.stereotype.Component;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

@Component
public class SetupWebDriverManager {
    DriverManagerType type;

    public void setType(DriverManagerType type) {
        this.type = type;
        WebDriverManager.getInstance(type).setup();
    }
}