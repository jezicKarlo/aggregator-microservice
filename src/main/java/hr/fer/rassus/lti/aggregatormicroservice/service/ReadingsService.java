package hr.fer.rassus.lti.aggregatormicroservice.service;

import hr.fer.rassus.lti.aggregatormicroservice.config.ConfigurationData;
import hr.fer.rassus.lti.aggregatormicroservice.repository.ReadingsRepository;
import hr.fer.rassus.lti.aggregatormicroservice.response.Reading;

public class ReadingsService {

    private ReadingsRepository temperatureRepository;
    private ReadingsRepository humidityRepository;
    private ConfigurationData config;

    public ReadingsService(ConfigurationData config) {
        this.config = config;
        temperatureRepository = new ReadingsRepository(config.getTemperatureMicroservice());
        humidityRepository = new ReadingsRepository(config.getTemperatureMicroservice());
    }

    public Reading getReading() {
        Integer temperature = temperatureRepository.fetch();
        Integer humidity = humidityRepository.fetch();

        return new Reading(temperature + config.getTemperatureUnit(),
                humidity + config.getHumidityUnit());
    }
}
