package com.api.tests;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.api.services.EpisodeService;
import com.api.utils.JsonUtils;

import io.restassured.response.Response;

/**
 * Test suite for Episode API.
 */
public class EpisodeServiceTests {

    private final EpisodeService episodeService = new EpisodeService();

    @Test
    @DisplayName("Get all episodes")
    public void testGetAllEpisodes() {
        Response response = episodeService.getAllEpisodes();
        episodeService.validateResponse(response, 200);
    }

    @Test
    @DisplayName("Get single episode by ID")
    public void testGetEpisodeById() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/episode-data.json");
        for (Map<String, Object> data : testData) {
            int id = (int) data.get("id");
            String expectedName = (String) data.get("name");

            Response response = episodeService.getEpisodeById(id);
            episodeService.validateResponse(response, 200);
            response.then().body("name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Get multiple episodes by IDs")
    public void testGetMultipleEpisodes() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/episode-data.json");
        int[] ids = testData.stream().mapToInt(data -> (int) data.get("id")).toArray();

        Response response = episodeService.getMultipleEpisodes(ids);
        episodeService.validateResponse(response, 200);

        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            String expectedName = (String) testData.get(i).get("name");
            response.then().body("[" + i + "].id", equalTo(id));
            response.then().body("[" + i + "].name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Filter episodes by fields")
    public void testFilterEpisodesByFields() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/episode-filter-data.json");
        for (Map<String, Object> data : testData) {
            String filterKey = (String) data.get("filter_key");
            String filterValue = (String) data.get("filter_value");

            Response response = episodeService.filterEpisodes(filterKey, filterValue);
            episodeService.validateResponse(response, 200);
            response.then().body("results[0]." + filterKey, equalTo(filterValue));
        }
    }

    @Test
    @DisplayName("Get episode by invalid ID")
    public void testGetEpisodeByInvalidId() {
        Response response = episodeService.getEpisodeById(-1);
        episodeService.validateResponse(response, 404);
        response.then().body("error", equalTo("Episode not found"));
    }
}
