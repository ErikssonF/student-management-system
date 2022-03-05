package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.errorMessage.EntityNotFoundException;
import se.iths.errorMessage.ErrorMessage;
import se.iths.errorMessage.InvalidDataException;
import se.iths.service.SubjectService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {

        if(subject.getSubjectName().isEmpty() || subject.getStudent().isEmpty())
            throw new InvalidDataException(new ErrorMessage(
                    "400",
                    "Invalid data used for request",
                    "/students"));

        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @PUT

    public Response updateSubject(@PathParam("id") Long id, Subject subject) {

        Optional<Subject> foundSubject = subjectService.getSubjectById(id);

        if (foundSubject.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No subject with ID: " + id + " was found",
                    "/students/" + id));


        subjectService.updateSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects(@QueryParam("subjects") List<String> subjects) {

        List<Subject> foundSubject = subjectService.getAllSubjects();

        if (foundSubject.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage(
                    "404",
                    "No subjects were found.",
                    "/students"));

        return Response.ok(foundSubject).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {

        Optional<Subject> foundSubject = subjectService.getSubjectById(id);

        if (foundSubject.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        subjectService.deleteSubject(id);

        return Response.ok(foundSubject).build();
    }
}