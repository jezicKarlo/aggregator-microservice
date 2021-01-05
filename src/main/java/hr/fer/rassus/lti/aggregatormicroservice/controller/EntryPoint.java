package hr.fer.rassus.lti.aggregatormicroservice.controller;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryPoint {

    private final ConfigurationData configurationData;

    public EntryPoint(ConfigurationData configurationData) {
        this.configurationData = configurationData;
    }

    @RequestMapping("/temperatureName")
    public String temperatureName() {
        return configurationData.getTemperatureName();
    }
}