package com.vanna.apiintegrations.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RESTIntegrationClient {

    @Value("${ws.users.url}")
    private String usersURI;

    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        UsersResponse response = restTemplate.getForObject(usersURI+"/users", UsersResponse.class);
        return response.getData();
    }
}
