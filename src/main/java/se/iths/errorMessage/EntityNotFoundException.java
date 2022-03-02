package se.iths.errorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EntityNotFoundException extends WebApplicationException {

    public EntityNotFoundException(ErrorMessage errorMessage) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build());
    }
}