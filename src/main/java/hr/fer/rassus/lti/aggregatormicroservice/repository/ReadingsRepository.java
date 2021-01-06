package hr.fer.rassus.lti.aggregatormicroservice.repository;

import feign.Feign;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.jaxrs.JAXRSContract;
import hr.fer.rassus.lti.aggregatormicroservice.api.ReadingsAPI;

public class ReadingsRepository {

    private ReadingsAPI api;
    private String url;

    public ReadingsRepository() {
    }

    public ReadingsRepository(String url) {
        build(url);
        this.url = url;
    }

    public boolean hasToRebuild(String newUrl) {
        if (url == null) {
            return true;
        }
        return !url.equals(newUrl);
    }

    public String fetch() {
        return api.fetchReadings();
    }

    private void build(String url) {
        api = Feign.builder()
                .contract(new JAXRSContract())
                .decoder(new GsonDecoder())
                .retryer(new Retryer.Default())
                .target(ReadingsAPI.class, url);
    }
}
