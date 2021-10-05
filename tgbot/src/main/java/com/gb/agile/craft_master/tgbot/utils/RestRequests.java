package com.gb.agile.craft_master.tgbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


public class RestRequests {
    private final String USERS_API_URL = "http://localhost:8189/craftmaster/api/v1/users/";
    private final String AUTH_API_URL = "http://localhost:8189/craftmaster/auth/user_login";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public RestRequests() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void createUser(String login, String name, String password) throws JsonProcessingException {

        JSONObject userJson = new JSONObject();
        userJson.put("login", login);
        userJson.put("name", name);
        userJson.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        String response = restTemplate.postForObject(USERS_API_URL, request, String.class);
        JsonNode root = objectMapper.readTree(response);
    }

    public void loginUser(String login, String password) throws JsonProcessingException {
        JSONObject userJson = new JSONObject();
        userJson.put("login", login);
        userJson.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        String response = restTemplate.postForObject(AUTH_API_URL, request, String.class);
        JsonNode root = objectMapper.readTree(response);
        token = root.get("token").toString();
    }
}
