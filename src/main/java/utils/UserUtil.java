package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    private static final double LAT_MIN = -40;
    private static final double LAT_MAX = 5;
    private static final double LON_MIN = 5;
    private static final double LON_MAX = 100;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Get users belonging to FanCode city
    public static List<JsonNode> getUsersInFancodeCity(Response usersResponse) throws IOException {
        JsonNode users = objectMapper.readTree(usersResponse.getBody().asString());
        List<JsonNode> fancodeUsers = new ArrayList<>();
        for (JsonNode user : users) {
            JsonNode geo = user.get("address").get("geo");
            double lat = geo.get("lat").asDouble();
            double lon = geo.get("lng").asDouble();
            if (lat >= LAT_MIN && lat <= LAT_MAX && lon >= LON_MIN && lon <= LON_MAX) {
                fancodeUsers.add(user);
            }
        }
        return fancodeUsers;
    }

    // Get the completion percentage of todos for a user
    public static double calculateTodoCompletionPercentage(Response todosResponse, int userId) throws IOException {
        JsonNode todos = objectMapper.readTree(todosResponse.getBody().asString());
        int totalTodos = 0;
        int completedTodos = 0;
        for (JsonNode todo : todos) {
            if (todo.get("userId").asInt() == userId) {
                totalTodos++;
                if (todo.get("completed").asBoolean()) {
                    completedTodos++;
                }
            }
        }
        if (totalTodos == 0) {
            return 0;  // Avoid division by zero if there are no todos
        }
        return (completedTodos * 100.0) / totalTodos;
    }
}
