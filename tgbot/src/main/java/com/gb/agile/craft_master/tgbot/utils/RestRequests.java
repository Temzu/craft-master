package com.gb.agile.craft_master.tgbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.gb.agile.craft_master.tgbot.entities.OccupationDto;
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
    private final String OCCUPATION_API_URL = HOST + API + "occupations/";
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
            headers.setBearerAuth(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String offerArrayToString (OfferDto[] offers) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < offers.length; i++) {
            sb.append(offers[i].getId())
                .append(") ")
                .append(offers[i].getTitle())
                .append(" [")
                .append(offers[i].getCreator().getName())
                .append("]")
                .append('\n');
        }
        return sb.toString();
    }
    public String getOffers() throws JsonProcessingException {
        String response = restTemplate.getForObject(OFFERS_API_URL + "nonpaged/", String.class);
        ObjectReader reader = objectMapper.readerFor(OfferDto[].class);
        OfferDto[] list = reader.readValue(response);
        return offerArrayToString(list);
    }

    public String getOrders() throws JsonProcessingException {
        String response = restTemplate.getForObject(ORDERS_API_URL, String.class);
        ObjectReader reader = objectMapper.readerFor(OrderDto[].class);
        OrderDto[] list = reader.readValue(response);
        return Arrays.toString(list);
    }

    public String getOccupations(Integer id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(OCCUPATION_API_URL +
                (id == null ? "" : id.toString()), HttpMethod.GET, request, String.class);
        ObjectReader reader = objectMapper.readerFor(OccupationDto[].class);
        OccupationDto[] list = reader.readValue(response.getBody());
        return occupationArrayToString(list);
    }

    private String occupationArrayToString(OccupationDto[] occupations) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < occupations.length; i++) {
            sb.append(occupations[i].getId())
                    .append(") ")
                    .append(occupations[i].getName());
            if (occupations[i].getChild().length > 0) sb.append('>');
            sb.append('\n');
        }
        return sb.toString();
    }

    public void createBid(int offerId, String userLogin) {
        HttpEntity<String> request = new HttpEntity<>("boby", headers);
        try {
            String response = restTemplate.postForObject(
                    String.format("%sbyoffer/offerid=%d&userlogin=%s",OFFERS_API_URL, offerId, userLogin),
                    request, String.class);
        } catch (Exception e) {
        }
    }
}
