package hr.fer.rassus.lti.aggregatormicroservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryPoint {

    @Value("${temperatureName}")
    private String temperatureName;

    @RequestMapping("/temperatureName")
    public String temperatureName() {
        return temperatureName;
    }
}
