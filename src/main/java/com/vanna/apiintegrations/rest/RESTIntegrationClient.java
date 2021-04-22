package com.vanna.apiintegrations.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RESTIntegrationClient {

    private static final Logger LOGGER = LogManager.getLogger(RESTIntegrationClient.class);

    @Value("${ws.users.url}")
    private String usersURI;

    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        UsersResponse response = restTemplate.getForObject(usersURI+"/users", UsersResponse.class);
        LOGGER.info("Receiving response for REST API");
        LOGGER.info("Count of available users: "+response.getData().size());
        return response.getData();
    }

    public UserCreationResponse postUsers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("sampleusername123", "SamplePassword@123");
        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(new UserRequest("Harry Potter", "CEO"), headers);
        ResponseEntity<UserCreationResponse> response =
                restTemplate.exchange(usersURI + "/api/users", HttpMethod.POST, requestEntity, UserCreationResponse.class);
        LOGGER.info(response.getBody().toString());
        return response.getBody();
    }
}
