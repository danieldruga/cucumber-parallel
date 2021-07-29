package com.ddruga.test.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.ddruga.test.selenium.util.CustomWaits;

/**
 * Marker class for all the PageObject classes
 */
public abstract class AbstractPageObject {

    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isElementVisible(By element, int seconds) {
        try {
            CustomWaits.untilElementIsVisible(driver, element, seconds);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    protected boolean isElementVisible(By element) {
        try {
            CustomWaits.untilElementIsVisible(driver, element);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

}
