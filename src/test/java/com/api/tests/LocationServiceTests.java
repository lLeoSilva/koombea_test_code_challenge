package com.api.tests;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.api.services.LocationService;
import com.api.utils.JsonUtils;

import io.restassured.response.Response;

/**
 * Test suite for Location API.
 */
public class LocationServiceTests {

    private final LocationService locationService = new LocationService();

    @Test
    @DisplayName("Get all locations")
    public void testGetAllLocations() {
        Response response = locationService.getAllLocations();
        locationService.validateResponse(response, 200);
    }

    @Test
    @DisplayName("Get single location by ID")
    public void testGetLocationById() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/location-data.json");
        for (Map<String, Object> data : testData) {
            int id = (int) data.get("id");
            String expectedName = (String) data.get("name");

            Response response = locationService.getLocationById(id);
            locationService.validateResponse(response, 200);
            response.then().body("name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Get multiple locations by IDs")
    public void testGetMultipleLocations() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/location-data.json");
        int[] ids = testData.stream().mapToInt(data -> (int) data.get("id")).toArray();

        Response response = locationService.getMultipleLocations(ids);
        locationService.validateResponse(response, 200);

        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            String expectedName = (String) testData.get(i).get("name");
            response.then().body("[" + i + "].id", equalTo(id));
            response.then().body("[" + i + "].name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Filter locations by fields")
    public void testFilterLocationsByFields() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/location-filter-data.json");
        for (Map<String, Object> data : testData) {
            String filterKey = (String) data.get("filter_key");
            String filterValue = (String) data.get("filter_value");

            Response response = locationService.filterLocations(filterKey, filterValue);
            locationService.validateResponse(response, 200);
            response.then().body("results[0]." + filterKey, equalTo(filterValue));
        }
    }

    @Test
    @DisplayName("Get location by invalid ID")
    public void testGetLocationByInvalidId() {
        Response response = locationService.getLocationById(-1);
        locationService.validateResponse(response, 404);
        response.then().body("error", equalTo("Location not found"));
    }
}
