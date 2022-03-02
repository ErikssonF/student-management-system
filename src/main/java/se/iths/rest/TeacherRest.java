package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.errorMessage.EntityNotFoundException;
import se.iths.errorMessage.ErrorMessage;
import se.iths.service.EntityService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    EntityService<Teacher> entityService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        entityService.create(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @PUT

    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {

        Optional<Teacher> foundStudent = entityService.getById(id);

        if (foundStudent.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No teacher with ID: " + id + " was found",
                    "/students/" + id));


        entityService.update(teacher);
        return Response.ok(teacher).build();
    }

    @Path("lastname")
    @GET
    public Response getTeacher(@QueryParam("lastname") String lastName) {

        List<Teacher> foundStudent = entityService.getTeacherByLastname(lastName);

        if (foundStudent.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage(
                    "404",
                    "No teacher with lastname: " + lastName + " was found",
                    "/teachers/" + lastName));

        return Response.ok(foundStudent).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers(@QueryParam("teachers") List<String> teachers) {

        List<Teacher> foundStudent = entityService.getAll();

        if (foundStudent.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage(
                    "404",
                    "No teachers were found.",
                    "/students"));

        return Response.ok(foundStudent).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {

        Optional<Teacher> foundStudent = entityService.getById(id);

        if (foundStudent.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        entityService.delete(id);

        return Response.ok(foundStudent).build();
    }
}
