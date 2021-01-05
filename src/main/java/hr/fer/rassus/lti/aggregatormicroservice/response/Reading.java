package hr.fer.rassus.lti.aggregatormicroservice.response;

import lombok.Data;

@Data
public class Reading {

    private String temperature;
    private String humidity;

    public Reading(String temperature, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
