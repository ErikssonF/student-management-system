package se.iths.errorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidDataException extends WebApplicationException {

    public InvalidDataException(ErrorMessage errorMessage) {
    super(Response.status(Response.Status.NOT_FOUND)
            .entity(errorMessage)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build());

    }
}
