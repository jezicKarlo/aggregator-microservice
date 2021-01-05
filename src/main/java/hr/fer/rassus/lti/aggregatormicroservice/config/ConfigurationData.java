package hr.fer.rassus.lti.aggregatormicroservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigurationData {

    @Value("${temperatureName}")
    private String temperatureName;
}
