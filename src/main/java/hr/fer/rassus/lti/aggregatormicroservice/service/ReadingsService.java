package hr.fer.rassus.lti.aggregatormicroservice.service;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import hr.fer.rassus.lti.aggregatormicroservice.repository.ReadingsRepository;
import hr.fer.rassus.lti.aggregatormicroservice.response.Reading;
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
        buildRepositories();
    }

    private void buildRepositories() {
        buildTemperatureRepository();
        buildHumidityRepository();
    }

    private void buildTemperatureRepository() {
        ServiceInstance temperatureMicroservice = discoveryClient.getInstances(config.getTemperatureMicroservice()).get(0);
        if (temperatureRepository.hasToRebuild(temperatureMicroservice.getUri().toString())) {
            temperatureRepository = new ReadingsRepository(temperatureMicroservice.getUri().toString());
        }
    }

    private void buildHumidityRepository() {
        ServiceInstance humidityMicroservice = discoveryClient.getInstances(config.getHumidityMicroserviceName()).get(0);
        if (humidityRepository.hasToRebuild(humidityMicroservice.getUri().toString())) {
            humidityRepository = new ReadingsRepository(humidityMicroservice.getUri().toString());
        }
    }

    public Reading getReading() {
        rebuildRepositories();
        String temperature = temperatureRepository.fetch();
        String humidity = humidityRepository.fetch();

        return new Reading(temperature, humidity);
    }

    private void rebuildRepositories() {
        buildRepositories();
    }

    public List<ServiceInstance> getInstance(String applicationName) {
        return discoveryClient.getInstances(applicationName);
    }
}
