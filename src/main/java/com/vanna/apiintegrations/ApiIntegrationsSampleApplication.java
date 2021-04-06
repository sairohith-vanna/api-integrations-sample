package com.vanna.apiintegrations;

import com.vanna.apiintegrations.soap.SOAPIntegrationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiIntegrationsSampleApplication implements CommandLineRunner {

	@Autowired
	private SOAPIntegrationClient integrationClient;

	public static void main(String[] args) {
		SpringApplication.run(ApiIntegrationsSampleApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		integrationClient.getCapitalCityForCountry("IND");
	}
}
