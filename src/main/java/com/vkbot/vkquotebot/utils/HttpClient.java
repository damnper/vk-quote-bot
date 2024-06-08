package com.vkbot.vkquotebot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class HttpClient {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Sends a POST request.
     *
     * @param url  the URL to send the request to
     * @param body the body of the request
     */
    public void sendPostRequest(String url, String body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            ResponseEntity<String> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            log.info("Response from VK: {}", response.getBody());
        } catch (Exception e) {
            log.error("Error sending request to VK: {}", e.getMessage());
        }
    }
}
