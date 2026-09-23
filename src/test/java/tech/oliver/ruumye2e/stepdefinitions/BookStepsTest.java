package tech.oliver.ruumye2e.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tech.oliver.ruumye2e.config.RestConfig;
import tech.oliver.ruumye2e.config.ScenarioContext;

public class BookStepsTest {

    private ScenarioContext scenarioContext;
    private RestConfig restConfig;

    public BookStepsTest(ScenarioContext scenarioContext, RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
    }

    @And("the room has no bookings for today")
    public void theRoomHasNoBookingsForToday() {

    }

    @When("I book the room for one hour from now")
    public void iBookTheRoomForOneHourFromNow(){

    }

    @Then("then room should be successfully booked")
    public void themRoomShouldBeSuccessfullyBooked(){

    }
}
