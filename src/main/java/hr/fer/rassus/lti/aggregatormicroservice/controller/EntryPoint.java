package hr.fer.rassus.lti.aggregatormicroservice.controller;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import hr.fer.rassus.lti.aggregatormicroservice.models.Reading;
import hr.fer.rassus.lti.aggregatormicroservice.service.ReadingsService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntryPoint {
    private ReadingsService service;

    public EntryPoint(ConfigurationData configurationData, DiscoveryClient discoveryClient) {
        service = new ReadingsService(configurationData, discoveryClient);
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return service.getInstance(applicationName);
    }

    @GetMapping("current-readings")
    public Reading temperatureName() {
        return service.getReading();
    }
}
