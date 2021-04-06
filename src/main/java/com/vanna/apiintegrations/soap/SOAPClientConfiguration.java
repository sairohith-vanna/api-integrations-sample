package com.vanna.apiintegrations.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SOAPClientConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.vanna.apiintegrations.wsdl.countryinformation");
        return marshaller;
    }

    @Bean
    public SOAPIntegrationClient integrationClient(Jaxb2Marshaller marshaller) {
        SOAPIntegrationClient integrationClient = new SOAPIntegrationClient();
        integrationClient.setMarshaller(marshaller());
        integrationClient.setUnmarshaller(marshaller());
        return integrationClient;
    }
}
