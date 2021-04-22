package com.vanna.apiintegrations;

import com.vanna.apiintegrations.rest.RESTIntegrationClient;
import com.vanna.apiintegrations.rest.User;
import com.vanna.apiintegrations.rest.UserCreationResponse;
import com.vanna.apiintegrations.soap.SOAPIntegrationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("integration/ws")
public class APIIntegrationController {

    private final SOAPIntegrationClient integrationClient;
    private final RESTIntegrationClient restClient;

    @Autowired
    public APIIntegrationController(SOAPIntegrationClient integrationClient, RESTIntegrationClient restClient) {
        this.integrationClient = integrationClient;
        this.restClient = restClient;
    }

    @GetMapping("soap/countries/capital/{countryISO}")
    public ResponseEntity<?> getCapitalCity(@PathVariable("countryISO") String countryCode) {
        APIResponse response;
        try {
            String capitalCity = integrationClient.getCapitalCityForCountry(countryCode);
            response = new APIResponse(capitalCity, "SOAP_API_INVOC_SUCCESS",
                    "The SOAP API has been invoked successfully");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            response = new APIResponse(null, "SOAP_API_INVOC_FAILURE",
                    "The SOAP API invocation ran into an error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("rest/users")
    public ResponseEntity<?> getUsersList() {
        APIResponse response;
        try {
            List<User> userList = restClient.getUsers();
            response = new APIResponse(userList, "REST_API_CALL_SUCCESS", "The REST API call is successful");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            response = new APIResponse(null, "REST_API_CALL_FAILURE",
                    "The REST API call ran into an error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("rest/users/create")
    public ResponseEntity<?> createSampleUser() {
        APIResponse response;
        try {
            UserCreationResponse userCreationResponse = restClient.postUsers();
            response = new APIResponse(userCreationResponse, "REST_API_CALL_SUCCESS", "The REST API call is successful");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new APIResponse(null, "REST_API_CALL_FAILURE",
                    "The REST API call ran into an error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
