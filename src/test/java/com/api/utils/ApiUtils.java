package com.api.utils;

import io.restassured.RestAssured;

public class ApiUtils {

    public static void setup() {
        RestAssured.baseURI = "https://rickandmortyapi.com/api";
    }
}