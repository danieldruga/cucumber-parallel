package com.ddruga.test.selenium.steps;

import org.openqa.selenium.WebDriver;

import com.ddruga.test.selenium.util.CustomWaits;

import io.cucumber.java.en.Given;

public class UtilitySteps extends AbstractSteps {

    @Given("I wait {int} seconds")
    public void iWaitGivenSeconds(int waitTime) {
        CustomWaits.sleep(waitTime * 1000);
    }

    @Given("I access {string}")
    public void iRefreshTheApplication(String website) {
        WebDriver driver = scenarioContext.getDriver();
        driver.get(website);
    }
}
