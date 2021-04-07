package com.vanna.apiintegrations.soap;

import com.vanna.apiintegrations.rest.RESTIntegrationClient;
import com.vanna.apiintegrations.wsdl.countryinformation.CapitalCity;
import com.vanna.apiintegrations.wsdl.countryinformation.CapitalCityResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SOAPIntegrationClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LogManager.getLogger(SOAPIntegrationClient.class);

    @Value("${ws.countries.url}")
    private String countriesURI;

    public String getCapitalCityForCountry(String countryISOCode) {
        CapitalCity capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode(countryISOCode);
        CapitalCityResponse city = (CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(countriesURI, capitalCity);
        LOGGER.info("Capital city for "+countryISOCode+ ": "+city.getCapitalCityResult());
        return city.getCapitalCityResult();
    }

}
