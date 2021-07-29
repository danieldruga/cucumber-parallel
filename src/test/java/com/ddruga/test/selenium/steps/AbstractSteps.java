package com.ddruga.test.selenium.steps;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ddruga.test.selenium.context.ScenarioContext;
import com.ddruga.test.selenium.pageobjects.AbstractPageObject;

/**
 * Marker class for all the PageObject classes
 */
@SpringBootTest
public abstract class AbstractSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSteps.class);

    @Autowired
    protected ScenarioContext scenarioContext;
    protected SessionId sessionId;

    protected <T extends AbstractPageObject> T getPageObjectInstance(Class<T> cls, T page) {
        T result;

        try {

            if (page == null || sessionId != scenarioContext.getCurrentDriverSessionId()) {
                result = cls.getDeclaredConstructor(WebDriver.class).newInstance(scenarioContext.getDriver());
                sessionId = scenarioContext.getCurrentDriverSessionId();
                return result;
            }
            return page;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            LOGGER.error("Cannot instantiate given class", e);
            throw new IllegalArgumentException("Cannot instantiate given class");
        }
    }
}
