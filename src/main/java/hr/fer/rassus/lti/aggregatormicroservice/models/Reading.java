package hr.fer.rassus.lti.aggregatormicroservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reading {
    private String temperature;
    private String temperatureUnit;
    private String humidity;
}
