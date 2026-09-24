package tech.oliver.ruumye2e.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import tech.oliver.ruumye2e.config.RestConfig;
import tech.oliver.ruumye2e.config.ScenarioContext;

public class UnbookStepTest {

    private final ScenarioContext scenarioContext;
    private final RestConfig restConfig;

    public UnbookStepTest(ScenarioContext scenarioContext,
                          RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
    }

    @When("I try to unbook the room")
    public void iTryToUnbookTheRoom() {
        var bookNumber = scenarioContext.get("bookNumber", Long.class);

        var response = restConfig.givenBackEnd()
                .pathParam("bookNumber", bookNumber)
                .delete("/bookings/{bookNumber}");

        scenarioContext.put("response", response);
    }

    @Then("the room should be successfully unbooked")
    public void theRoomShouldBeSuccessfullyUnbooked() {
       var response = scenarioContext.get("response", Response.class);

       response.then()
               .statusCode(HttpStatus.OK.value());
    }

    @And("The bookings time as already passed")
    public void theBookingsTimeAsAlreadyPassed() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Then("the room cannot be unbooked")
    public void theRoomCannotBeUnbooked() {
        var response = scenarioContext.get("response", Response.class);

        response.then()
                .statusCode(HttpStatus.UNPROCESSABLE_CONTENT.value());
    }
}
