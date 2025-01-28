package com.api.services;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.base.BaseService;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Service layer for Location API interactions.
 */
public class LocationService extends BaseService {

    private static final String LOCATION_ENDPOINT = "/location";

    /**
     * Get all locations.
     */
    public Response getAllLocations() {
        return given().spec(requestSpecification())
                .when().get(LOCATION_ENDPOINT);
    }

    /**
     * Get a single location by ID.
     */
    public Response getLocationById(int id) {
        return given().spec(requestSpecification())
                .when().get(LOCATION_ENDPOINT + "/" + id);
    }

    /**
     * Get multiple locations by IDs.
     */
    public Response getMultipleLocations(int[] ids) {
        String idParam = Arrays.stream(ids)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(","));
        
        return given().spec(requestSpecification())
                    .when().get(LOCATION_ENDPOINT + "/" + idParam);
    }

    /**
     * Filter locations using a dynamic query from JSON input.
     */
    public Response filterLocations(String filterKey, String filterValue) {
        return given().spec(requestSpecification())
                .queryParam(filterKey, filterValue)
                .when().get(LOCATION_ENDPOINT);
    }

    /**
     * Filter locations using multiple parameters.
     */
    public Response filterLocations(Map<String, String> filters) {
        return given().spec(requestSpecification())
                .queryParams(filters)
                .when().get(LOCATION_ENDPOINT);
    }
}