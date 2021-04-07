package com.vanna.apiintegrations.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
}
