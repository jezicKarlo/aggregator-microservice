package hr.fer.rassus.lti.aggregatormicroservice.repository;

import feign.Feign;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.jaxrs.JAXRSContract;
import hr.fer.rassus.lti.aggregatormicroservice.api.ReadingsAPI;

public class ReadingsRepository {

    private ReadingsAPI api;

    public ReadingsRepository(String url) {
        api = Feign.builder()
                .contract(new JAXRSContract())
                .decoder(new GsonDecoder())
                .retryer(new Retryer.Default())
                .target(ReadingsAPI.class, url);
    }

    public Integer fetch() {
        return api.fetchReadings();
    }
}
