package com.ddruga.test.selenium.runner;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddruga.test.selenium.config.Config;
import com.ddruga.test.selenium.context.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CucumberHooks implements ConcurrentEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberHooks.class);

    @Autowired
    private ScenarioContext scenarioContext;

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioContext.getNewDriverInstance();
        scenarioContext.setScenario(scenario);
        LOGGER.info("Driver initialized for scenario - {}", scenario.getName());
        // some business logic
    }

    @After
    public void afterScenario() {
        Scenario scenario = scenarioContext.getScenario();
        WebDriver driver = scenarioContext.getDriver();

        takeErrorScreenshot(scenario, driver);
        LOGGER.info("Driver will close for scenario - {}", scenario.getName());

        driver.quit();
    }

    private void takeErrorScreenshot(Scenario scenario, WebDriver driver) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure");
        }
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, beforeAll);
        eventPublisher.registerHandlerFor(TestRunFinished.class, afterAll);
    }

    private EventHandler<TestRunStarted> beforeAll = event -> {
        // something that needs doing before everything
        WebDriverManager.getInstance(Config.getBrowserType()).setup();

    };

    private EventHandler<TestRunFinished> afterAll = event -> {

    };

}