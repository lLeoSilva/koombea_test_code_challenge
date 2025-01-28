package com.api.base;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Base service providing shared functionality for API interactions.
 */
public abstract class BaseService {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";

    /**
     * Creates a request specification with the base URL and default headers.
     *
     * @return A {@link RequestSpecification} instance.
     */
    protected RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/json")
                .build();
    }

    /**
     * Validates the response by checking the status code and common headers.
     *
     * @param response     The {@link Response} object to validate.
     * @param expectedCode The expected HTTP status code.
     */
    public void validateResponse(Response response, int expectedCode) {
        response.then()
                .statusCode(expectedCode)
                .header("Content-Type", containsString("application/json"))
                .header("Server", notNullValue());
    }

    /**
     * Sends a dynamic request with a query parameter.
     *
     * @param endpoint   The API endpoint.
     * @param queryKey   The query parameter key.
     * @param queryValue The query parameter value.
     * @return A {@link Response} object.
     */
    public Response sendRequestWithQuery(String endpoint, String queryKey, Object queryValue) {
        return given().spec(requestSpecification())
                .queryParam(queryKey, queryValue)
                .when().get(endpoint);
    }

    /**
     * Sends a dynamic request with multiple query parameters.
     *
     * @param endpoint The API endpoint.
     * @param params   The query parameters.
     * @return A {@link Response} object.
     */
    public Response sendRequestWithQueries(String endpoint, Map<String, String> params) {
        return given().spec(requestSpecification())
                .queryParams(params)
                .when().get(endpoint);
    }
}