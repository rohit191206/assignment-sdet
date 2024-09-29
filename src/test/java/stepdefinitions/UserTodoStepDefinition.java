package stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ApiClient;
import utils.UserUtil;
import java.io.IOException;
import java.util.List;

public class UserTodoStepDefinition {
    private Response usersResponse;
    private Response todosResponse;
    private List<JsonNode> fancodeUsers;

    @Given("User data is fetched from the API")
    public void userDataIsFetchedFromTheAPI() {
        usersResponse = ApiClient.get("/users");
    }

    @And("Todo data is fetched from the API")
    public void todoDataIsFetchedFromTheAPI() {
        todosResponse = ApiClient.get("/todos");
    }

    @When("Users belong to the city FanCode")
    public void usersBelongToTheCityFanCode() throws IOException {
        fancodeUsers = UserUtil.getUsersInFancodeCity(usersResponse);
    }

    @Then("Each user's completed todo percentage should be greater than {int}%")
    public void eachUserSCompletedTodoPercentageShouldBeGreaterThan(int percentage) throws IOException {
        for (JsonNode user : fancodeUsers) {
            int userId = user.get("id").asInt();
            double completionPercentage = UserUtil.calculateTodoCompletionPercentage(todosResponse, userId);
            Assert.assertTrue("User " + userId + " has less than 50% completion", completionPercentage > percentage);
        }
    }
}
