package com.ddruga.test.selenium.context;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.stereotype.Component;

import com.ddruga.test.selenium.config.Config;
import com.ddruga.test.selenium.config.CustomChromeDriver;
import com.ddruga.test.selenium.config.CustomEdgeDriver;

import io.cucumber.java.Scenario;
import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.config.DriverManagerType;

@Component
@ScenarioScope
public class ScenarioContext {

    private WebDriver driver;
    private Scenario scenario;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot() {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "ScreenCapture");
    }

    public void takeScreenshotForBug() {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "KnownBug");
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void getNewDriverInstance() {
        if (Config.getBrowserType().equals(DriverManagerType.CHROME)) {
            driver = CustomChromeDriver.getInstance();
        } else if (Config.getBrowserType().equals(DriverManagerType.EDGE)) {
            driver = CustomEdgeDriver.getInstance();
        }
    }

    public SessionId getCurrentDriverSessionId() {
        return ((RemoteWebDriver) driver).getSessionId();
    }

}
