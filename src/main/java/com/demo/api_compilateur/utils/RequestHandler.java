package com.demo.Api_Compilateur.utils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestHandler {
    public static final String JDOODLE_ENDPOINT = "https://api.jdoodle.com/execute";

    public static ResponseEntity<String> postRunRequest(String lang, String index, String program, String stdin) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = String.format("{\"script\":\"%s\",\"language\":\"%s\",\"versionIndex\":\"%s\",\"stdin\":\"%s\",\"clientId\":\"%s\",\"clientSecret\":\"%s\"}",
                program, lang, index, stdin, System.getenv("dev.JDOODLE_CLIENT_ID"), System.getenv("dev.JDOODLE_CLIENT_SECRET"));

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(JDOODLE_ENDPOINT, requestEntity, String.class);
    }
}


