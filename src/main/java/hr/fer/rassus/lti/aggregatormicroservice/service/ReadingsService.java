package hr.fer.rassus.lti.aggregatormicroservice.service;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import hr.fer.rassus.lti.aggregatormicroservice.models.Reading;
import hr.fer.rassus.lti.aggregatormicroservice.repository.ReadingsRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class ReadingsService {

    private ConfigurationData config;
    private DiscoveryClient discoveryClient;
    private ReadingsRepository temperatureRepository;
    private ReadingsRepository humidityRepository;

    public ReadingsService(ConfigurationData config, DiscoveryClient discoveryClient) {
        this.config = config;
        this.discoveryClient = discoveryClient;
        temperatureRepository = new ReadingsRepository();
        humidityRepository = new ReadingsRepository();
        buildRepositories();
    }

    private void buildRepositories() {
        buildTemperatureRepository();
        buildHumidityRepository();
    }

    private void buildTemperatureRepository() {
        discoveryClient.getInstances(config.getTemperatureMicroserviceName())
                        .stream()
                        .findAny()
                        .map(microservice -> microservice.getUri().toString())
                        .ifPresent(newUrl -> {
                            if (temperatureRepository.hasToRebuild(newUrl)) {
                                temperatureRepository = new ReadingsRepository(newUrl);
                            }
                        });
    }

    private void buildHumidityRepository() {
        discoveryClient.getInstances(config.getHumidityMicroserviceName())
                .stream()
                .findAny()
                .map(microservice -> microservice.getUri().toString())
                .ifPresent(newUrl -> {
                    if (humidityRepository.hasToRebuild(newUrl)) {
                        humidityRepository = new ReadingsRepository(newUrl);
                    }
                });
    }

    public Reading getReading() {
        buildRepositories();
        String unit = config.getTemperatureUnit();
        return Reading.builder()
                .temperature(adaptTemperature(temperatureRepository.fetch().getReading(), unit))
                .temperatureUnit(unit)
                .humidity(humidityRepository.fetch().getReading())
                .build();
    }

    private String adaptTemperature(String temperature, String unit) {
        if (unit.equals("K")) {
            return Double.parseDouble(temperature) + 273.15 + "";
        }
        return temperature;
    }

    public List<ServiceInstance> getInstance(String applicationName) {
        return discoveryClient.getInstances(applicationName);
    }
}
