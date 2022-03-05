package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.errorMessage.EntityNotFoundException;
import se.iths.errorMessage.ErrorMessage;
import se.iths.errorMessage.InvalidDataException;
import se.iths.service.TeacherService;
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
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        if(teacher.getFirstName().isEmpty() || teacher.getLastName().isEmpty())
            throw new InvalidDataException(new ErrorMessage(
                    "400",
                    "Invalid data used for request",
                    "/teachers"));

        teacherService.createTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {

        Optional<Teacher> foundTeacher = teacherService.getTeacherById(id);

        if (foundTeacher.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No teacher with ID: " + id + " was found",
                    "/teachers/" + id));


        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("lastname")
    @GET
    public Response getTeacher(@QueryParam("lastname") String lastName) {

        List<Teacher> foundTeacher = teacherService.getTeacherByLastName(lastName);

        if (foundTeacher.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage(
                    "404",
                    "No teacher with lastname: " + lastName + " was found",
                    "/teachers/" + lastName));

        return Response.ok(foundTeacher).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers(@QueryParam("teachers") List<String> teachers) {

        List<Teacher> foundTeacher = teacherService.getAllTeachers();

        if (foundTeacher.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage(
                    "404",
                    "No teachers were found.",
                    "/teachers"));

        return Response.ok(foundTeacher).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {

        Optional<Teacher> foundTeacher = teacherService.getTeacherById(id);

        if (foundTeacher.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        teacherService.deleteTeacher(id);

        return Response.ok(foundTeacher).build();
    }

}
