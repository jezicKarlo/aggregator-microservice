package hr.fer.rassus.lti.aggregatormicroservice.repository;

import feign.Feign;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.jaxrs.JAXRSContract;
import hr.fer.rassus.lti.aggregatormicroservice.api.ReadingsAPI;
import hr.fer.rassus.lti.aggregatormicroservice.models.ReadingResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReadingsRepository {
    private ReadingsAPI api;
    private String url;

    public ReadingsRepository(String url) {
        this.url = url;
        build(url);
    }

    public boolean hasToRebuild(String newUrl) {
        return url == null || !url.equals(newUrl);
    }

    public ReadingResponse fetch() {
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
