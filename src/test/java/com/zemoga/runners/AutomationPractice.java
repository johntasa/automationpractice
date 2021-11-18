package com.zemoga.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/automation_practice.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.zemoga.stepdefinitions"},
        tags = ""
)

public class AutomationPractice {
}