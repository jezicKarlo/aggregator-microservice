package hr.fer.rassus.lti.aggregatormicroservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigurationData {
    @Value("${microservice.temperature.name}")
    private String temperatureMicroservice;

    @Value("${microservice.humidity.name}")
    private String humidityMicroserviceName;

    @Value("${microservice.temperature.unit}")
    private String temperatureUnit;
}
