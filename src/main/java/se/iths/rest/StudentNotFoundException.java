package se.iths.rest;

import se.iths.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentNotFoundException extends WebApplicationException {

    public StudentNotFoundException(ErrorMessage errorMessage){
    super(Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }
}