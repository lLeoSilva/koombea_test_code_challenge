package com.api.services;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.base.BaseService;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Service layer for Episode API interactions.
 */
public class EpisodeService extends BaseService {

    private static final String EPISODE_ENDPOINT = "/episode";

    /**
     * Get all episodes.
     */
    public Response getAllEpisodes() {
        return given().spec(requestSpecification())
                .when().get(EPISODE_ENDPOINT);
    }

    /**
     * Get a single episode by ID.
     */
    public Response getEpisodeById(int id) {
        return given().spec(requestSpecification())
                .when().get(EPISODE_ENDPOINT + "/" + id);
    }

    /**
     * Get multiple episodes by IDs.
     */
    public Response getMultipleEpisodes(int[] ids) {
        String idParam = Arrays.stream(ids)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(","));
        
        return given().spec(requestSpecification())
                    .when().get(EPISODE_ENDPOINT + "/" + idParam);
    }

    /**
     * Filter episodes using a dynamic query from JSON input.
     */
    public Response filterEpisodes(String filterKey, String filterValue) {
        return given().spec(requestSpecification())
                .queryParam(filterKey, filterValue)
                .when().get(EPISODE_ENDPOINT);
    }

    /**
     * Filter episodes using multiple parameters.
     */
    public Response filterEpisodes(Map<String, String> filters) {
        return given().spec(requestSpecification())
                .queryParams(filters)
                .when().get(EPISODE_ENDPOINT);
    }
}