package hr.fer.rassus.lti.aggregatormicroservice.models;

import lombok.Data;

@Data
public class ReadingResponse {
    private String reading;
    private String message;
}
