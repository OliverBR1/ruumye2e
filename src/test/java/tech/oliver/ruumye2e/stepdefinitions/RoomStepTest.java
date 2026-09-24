package tech.oliver.ruumye2e.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import tech.oliver.ruumye2e.config.RestConfig;
import tech.oliver.ruumye2e.config.ScenarioContext;
import tech.oliver.ruumye2e.dto.RoomDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomStepTest {

    private final ScenarioContext scenarioContext;
    private final RestConfig restConfig;

    public RoomStepTest(ScenarioContext scenarioContext, RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
    }

    @When("I list all the rooms")
    public void iListAllTheRooms() {
        var response = restConfig.givenBackEnd()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/rooms");

        scenarioContext.put("response", response);
    }

    @Then("I detect that the room {string} exists")
    public void iDetectThatTheRoomNameExists(String name) {
        var response = scenarioContext.get("response", Response.class);

        var rooms = response.then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<RoomDto>>() {});

      assertTrue(rooms.stream().anyMatch(r -> r.name().equalsIgnoreCase(name)));
    }

    @Given("the room {string} exists")
    public void theRoomExists(String roomName ){

       var rooms =  restConfig.givenBackEnd()
                .contentType ( MediaType.APPLICATION_JSON_VALUE )
                .get ( "/rooms" )
                .then ( )
                .statusCode ( 200 )
                .extract()
                .as(new TypeRef<List<RoomDto>>() {});

       var room = rooms.stream()
                       .filter(r -> r.name().equalsIgnoreCase(roomName))
                       .findFirst();

       assertTrue(room.isPresent());

       scenarioContext.put("roomId", room.get().id());
    }
}
