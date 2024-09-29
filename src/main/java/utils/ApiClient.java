package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    public static Response get(String endpoint) {
        return RestAssured.get(BASE_URL + endpoint);
    }
}
