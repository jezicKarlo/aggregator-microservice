package hr.fer.rassus.lti.aggregatormicroservice.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface ReadingsAPI {
    @GET
    @Path("current-readings")
    String fetchReadings();
}
