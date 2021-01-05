package hr.fer.rassus.lti.aggregatormicroservice.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface ReadingsAPI {

    @GET
    @Path("{host}/current-readings")
    Integer fetchReadings(@PathParam("host") String host);
}
