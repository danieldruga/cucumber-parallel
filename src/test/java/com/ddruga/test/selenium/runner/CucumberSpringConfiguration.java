package com.ddruga.test.selenium.runner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ddruga.test.selenium.prerequisites.setup.ApplicationContextConfig;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
@SpringBootTest
public class CucumberSpringConfiguration {

}
