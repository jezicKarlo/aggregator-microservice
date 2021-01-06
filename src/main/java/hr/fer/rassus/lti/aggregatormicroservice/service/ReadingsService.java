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
        List<ServiceInstance> instances = discoveryClient.getInstances(config.getTemperatureMicroservice());
        if (instances.size() == 0) {
            return;
        }
        ServiceInstance temperatureMicroservice = instances.get(0);
        if (temperatureRepository.hasToRebuild(temperatureMicroservice.getUri().toString())) {
            temperatureRepository = new ReadingsRepository(temperatureMicroservice.getUri().toString());
        }
    }

    private void buildHumidityRepository() {
        List<ServiceInstance> instances = discoveryClient.getInstances(config.getHumidityMicroserviceName());
        if (instances.size() == 0) {
            return;
        }
        ServiceInstance humidityMicroservice = instances.get(0);
        if (humidityRepository.hasToRebuild(humidityMicroservice.getUri().toString())) {
            humidityRepository = new ReadingsRepository(humidityMicroservice.getUri().toString());
        }
    }

    public Reading getReading() {
        buildRepositories();
        return new Reading(
                adapt(temperatureRepository.fetch(),config.getTemperatureUnit()),
                humidityRepository.fetch()
        );
    }

    private String adapt(String temperature, String temperatureUnit) {
        if (temperatureUnit.equals("K")){
           return Double.parseDouble(temperature) + 273.15 + "";
        }
        return temperature;
    }

    public List<ServiceInstance> getInstance(String applicationName) {
        return discoveryClient.getInstances(applicationName);
    }
}
