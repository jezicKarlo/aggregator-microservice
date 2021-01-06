package hr.fer.rassus.lti.aggregatormicroservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reading {
    private String temperature;
    private String humidity;
}
