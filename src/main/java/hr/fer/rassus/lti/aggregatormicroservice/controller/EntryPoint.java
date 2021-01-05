package hr.fer.rassus.lti.aggregatormicroservice.controller;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import hr.fer.rassus.lti.aggregatormicroservice.response.Reading;
import hr.fer.rassus.lti.aggregatormicroservice.service.ReadingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryPoint {

    private ReadingsService service;

    public EntryPoint(ConfigurationData configurationData) {
        service = new ReadingsService(configurationData);
    }

    @GetMapping("/api/readings")
    public Reading temperatureName() {
        return service.getReading();
    }
}
