package com.appium.cucumber.Moneybook;

import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = {"json:target/cucumber.json"},features = "src/test/resource")

public class RunnerTest {

}
