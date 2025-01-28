package com.api.tests;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.api.services.CharacterService;
import com.api.utils.JsonUtils;

import io.restassured.response.Response;

/**
 * Test suite for Character API.
 */
public class CharacterServiceTests {
    private final CharacterService characterService = new CharacterService();

    @Test
    @DisplayName("Get all characters")
    public void testGetAllCharacters() {
        Response response = characterService.getAllCharacters();
        characterService.validateResponse(response, 200);
    }

    @Test
    @DisplayName("Get single character by ID")
    public void testGetCharacterById() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/character-data.json");
        for (Map<String, Object> data : testData) {
            int id = (int) data.get("id");
            String expectedName = (String) data.get("name");

            Response response = characterService.getCharacterById(id);
            characterService.validateResponse(response, 200);
            response.then().body("name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Get multiple characters by IDs")
    public void testGetMultipleCharacters() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/character-data.json");
        int[] ids = testData.stream().mapToInt(data -> (int) data.get("id")).toArray();

        Response response = characterService.getMultipleCharacters(ids);
        characterService.validateResponse(response, 200);

        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            String expectedName = (String) testData.get(i).get("name");
            response.then().body("[" + i + "].id", equalTo(id));
            response.then().body("[" + i + "].name", equalTo(expectedName));
        }
    }

    @Test
    @DisplayName("Filter characters by fields")
    public void testFilterCharactersByFields() {
        List<Map<String, Object>> testData = JsonUtils.readJsonAsList("/character-filter-data.json");
        for (Map<String, Object> data : testData) {
            String filterKey = (String) data.get("filter_key");
            String filterValue = (String) data.get("filter_value");

            Response response = characterService.filterCharacters(filterKey, filterValue);
            characterService.validateResponse(response, 200);
            response.then().body("results[0]." + filterKey, equalTo(filterValue));
        }
    }

    @Test
    @DisplayName("Get character by invalid ID")
    public void testGetCharacterByInvalidId() {
        Response response = characterService.getCharacterById(-1);
        characterService.validateResponse(response, 404);
        response.then().body("error", equalTo("Character not found"));
    }
}