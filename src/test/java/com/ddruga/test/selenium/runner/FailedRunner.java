package com.ddruga.test.selenium.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "@target/rerun.txt", glue = { "com.ddruga.test.selenium.steps",
        "com.ddruga.test.selenium.runner" }, plugin = { "json:target/cucumber2.json", "summary",
                "com.ddruga.test.selenium.runner.CucumberHooks" })
public class FailedRunner {

}
