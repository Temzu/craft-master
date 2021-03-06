package com.gb.agile.craft_master.tgbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.gb.agile.craft_master.tgbot.entities.OccupationDto;
import com.gb.agile.craft_master.tgbot.entities.OfferDto;
import com.gb.agile.craft_master.tgbot.entities.BidDto;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestRequests {
    private final String HOST = "http://localhost:8189/craftmaster/";
    private final String API = "api/v1/";
    private final String USERS_API_URL = HOST + API + "users/";
    private final String OFFERS_API_URL = HOST + API + "offers/";
    private final String BIDS_API_URL = HOST + API + "bids/";
    private final String OCCUPATION_API_URL = HOST + API + "occupations/";
    private final String AUTH_API_URL = HOST + "/auth/user_login";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String token;

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private BidDto[] bidsList;

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
            token = root.get("token").toString().replace("\"", "").replace("Bearer ", "");
            headers.setBearerAuth(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String offerArrayToString(OfferDto[] offers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offers.length; i++) {
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

    public String getBids() throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(BIDS_API_URL + "userofferbids",
                HttpMethod.GET, request, String.class);
        ObjectReader reader = objectMapper.readerFor(BidDto[].class);
        bidsList = reader.readValue(response.getBody());
        return bidArrayToString(bidsList);
    }

    private String bidArrayToString(BidDto[] bids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bids.length; i++) {
            sb.append(i + 1)
                    .append(") ")
                    .append(bids[i].getTitle())
                    .append(" [")
                    .append(bids[i].getPrice())
                    .append("]")
                    .append('\n');
        }
        return sb.toString();
    }

    public String getOccupations(Integer id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(OCCUPATION_API_URL +
                (id == null ? "/single" : id.toString()), HttpMethod.GET, request, String.class);
        ObjectReader reader = objectMapper.readerFor(id == null ? OccupationDto[].class : OccupationDto.class);
        if (id == null) {
            OccupationDto[] list = reader.readValue(response.getBody());
            return occupationArrayToString(list);
        } else {
            OccupationDto occupation = reader.readValue(response.getBody());
            return occupationArrayToString(occupation.getChildren());
        }

    }

    private String occupationArrayToString(OccupationDto[] occupations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < occupations.length; i++) {
            sb.append(occupations[i].getId())
                    .append(") ")
                    .append(occupations[i].getName());
            if (occupations[i].getChildren().length > 0) sb.append(BotCharacters.OCCUPATION_LIST_SIGN.value);
            sb.append('\n');
        }
        return sb.toString();
    }

    public void createBid(int offerId, String userLogin) {
        HttpEntity<String> request = new HttpEntity<>("boby", headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    String.format("%sbyoffer?offerid=%d&userlogin=%s", BIDS_API_URL, offerId, userLogin),
                    HttpMethod.GET, request, String.class);
        } catch (Exception e) {
        }
    }

    public void createOffer(OfferDto offer) {
        JSONObject offerJson = new JSONObject();
        offerJson.put("title", offer.getTitle());
        offerJson.put("description", offer.getDescription());
        offerJson.put("occupationId", offer.getOccupationId());
        offerJson.put("price", offer.getPrice());
        offerJson.put("user", offer.getCreator());

        HttpEntity<String> request = new HttpEntity<>(offerJson.toString(), headers);
        try {
            String response = restTemplate.postForObject(OFFERS_API_URL, request, String.class);
        } catch (Exception e) {
        }
    }

    public void acceptBid(int bidId) {
        BidDto bid = bidsList[bidId - 1];
        HttpEntity<String> request = new HttpEntity<>("boby", headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    String.format("%saccept/%s", BIDS_API_URL, bid.getId()),
                    HttpMethod.GET, request, String.class);
        } catch (Exception e) {
        }
    }
}
