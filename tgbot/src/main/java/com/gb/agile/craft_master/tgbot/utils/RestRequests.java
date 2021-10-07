package com.gb.agile.craft_master.tgbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.gb.agile.craft_master.tgbot.entities.OfferDto;
import com.gb.agile.craft_master.tgbot.entities.OrderDto;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestRequests {
    private final String HOST = "http://localhost:8189/craftmaster/";
    private final String API = "api/v1/";
    private final String USERS_API_URL = HOST + API + "users/";
    private final String OFFERS_API_URL = HOST + API + "offers/";
    private final String ORDERS_API_URL = HOST + API + "orders/";
    private final String AUTH_API_URL = HOST + "/auth/user_login";
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

    public boolean loginUser(String login, String password) throws JsonProcessingException {
        JSONObject userJson = new JSONObject();
        userJson.put("login", login);
        userJson.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        try {
            String response = restTemplate.postForObject(AUTH_API_URL, request, String.class);
            JsonNode root = objectMapper.readTree(response);
            token = root.get("token").toString().replace("\"","").replace("Bearer ","");
            //headers.setBearerAuth(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getOffers() throws JsonProcessingException {
        String response = restTemplate.getForObject(OFFERS_API_URL, String.class);
        ObjectReader reader = objectMapper.readerFor(OfferDto[].class);
        OfferDto[] list = reader.readValue(response);
        return Arrays.toString(list);
    }

    public String getOrders() throws JsonProcessingException {
        String response = restTemplate.getForObject(ORDERS_API_URL, String.class);
        ObjectReader reader = objectMapper.readerFor(OrderDto[].class);
        OrderDto[] list = reader.readValue(response);
        return Arrays.toString(list);
    }
}
