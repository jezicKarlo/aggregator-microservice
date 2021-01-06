package hr.fer.rassus.lti.aggregatormicroservice.api;

import hr.fer.rassus.lti.aggregatormicroservice.models.ReadingResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface ReadingsAPI {
    @GET
    @Path("current-reading")
    ReadingResponse fetchReadings();
}
