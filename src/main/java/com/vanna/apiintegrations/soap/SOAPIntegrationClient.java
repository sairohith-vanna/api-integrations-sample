package com.vanna.apiintegrations.soap;

import com.vanna.apiintegrations.wsdl.countryinformation.CapitalCity;
import com.vanna.apiintegrations.wsdl.countryinformation.CapitalCityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SOAPIntegrationClient extends WebServiceGatewaySupport {

    private final Logger LOGGER = LoggerFactory.getLogger("APIINTEGRATIONS::SOAPINTEGRATIONCLIENT");

    @Value("${ws.countries.url}")
    private String countriesURI;

    public void getCapitalCityForCountry(String countryISOCode) {
        CapitalCity capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode(countryISOCode);
        CapitalCityResponse city = (CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(countriesURI, capitalCity);
        LOGGER.info("Capital city for "+countryISOCode+ ": "+city.getCapitalCityResult());
    }

}
