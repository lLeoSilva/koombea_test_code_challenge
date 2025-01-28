package com.api.services;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.base.BaseService;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Service layer for Character API interactions.
 */
public class CharacterService extends BaseService {

    private static final String CHARACTER_ENDPOINT = "/character";

    /**
     * Get all characters.
     */
    public Response getAllCharacters() {
        return given().spec(requestSpecification())
                .when().get(CHARACTER_ENDPOINT);
    }

    /**
     * Get a single character by ID.
     */
    public Response getCharacterById(int id) {
        return given().spec(requestSpecification())
                .when().get(CHARACTER_ENDPOINT + "/" + id);
    }

    /**
     * Get multiple characters by IDs.
     */
    public Response getMultipleCharacters(int[] ids) {
        String idParam = Arrays.stream(ids)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(","));
        
        return given().spec(requestSpecification())
                    .when().get(CHARACTER_ENDPOINT + "/" + idParam);
    }

    /**
     * Filter characters using a dynamic query from JSON input.
     */
    public Response filterCharacters(String filterKey, String filterValue) {
        return given().spec(requestSpecification())
                .queryParam(filterKey, filterValue)
                .when().get(CHARACTER_ENDPOINT);
    }

    /**
     * Filter characters using multiple parameters.
     */
    public Response filterCharacters(Map<String, String> filters) {
        return given().spec(requestSpecification())
                .queryParams(filters)
                .when().get(CHARACTER_ENDPOINT);
    }
}