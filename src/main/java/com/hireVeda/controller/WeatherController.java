package com.hireVeda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hireVeda.excption.InvalidCredentialException;
import com.hireVeda.model.Root;
import com.hireVeda.model.Root2;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/weather")
class WeatherApplication {

    private final String apiKey = "630b8ed2cdmshfc95e0e3fc76c0ap1e997djsna4fa2663f6e4";

    private final Random random = new Random();

    private static final String CLIENT_ID = "101110a";
    private static final String CLIENT_SECRET = "testSecret1122@";



    @GetMapping("/{city}")
    public ResponseEntity<Root> getWeatherSummary(@RequestHeader("ClientId") String clientId,
                                                  @RequestHeader("ClientSecret") String clientSecret,
                                                  @PathVariable String city) {

        if (!clientId.equals(CLIENT_ID) || !clientSecret.equals(CLIENT_SECRET)) {
            throw new InvalidCredentialException("Wrong Client Id or Wrong Secret");
       }
        String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/summary/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Root> response = new RestTemplate().exchange(url, HttpMethod.GET, entity, Root.class);
        return ResponseEntity.ok(response.getBody());
    }


    @GetMapping("/hourlyApi")
    public ResponseEntity<Root2> getHourlySummary(@RequestHeader("ClientId") String clientId,
                                                  @RequestHeader("ClientSecret") String clientSecret) {

        if (!clientId.equals(CLIENT_ID) || !clientSecret.equals(CLIENT_SECRET)) {
            throw new InvalidCredentialException("Wrong Client Id or Wrong Secret");
        }

        // Make API request to Rapid API Weather API
        String apiUrl = "https://open-weather13.p.rapidapi.com/city/new%20jersey/EN";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "open-weather13.p.rapidapi.com");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = new RestTemplate().exchange(apiUrl, HttpMethod.GET, entity, String.class);


        ObjectMapper mapper = new ObjectMapper();
        try {
            Root2 root = mapper.readValue(response.getBody(), Root2.class);
            return ResponseEntity.ok(root);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}