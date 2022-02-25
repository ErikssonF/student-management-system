package se.iths.rest;


import se.iths.ErrorMessage;
import se.iths.entity.Student;
import se.iths.service.StudentService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student){

        if(student.getFirstName().isEmpty() || student.getLastName().isEmpty() ||student.getEmail().isEmpty())
            throw new InvalidDataException(new ErrorMessage("400", "Invalid data used for request", "/students"));

        studentService.createStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {

        Optional<Student> foundStudent = studentService.getStudentById(id);

        if(foundStudent.isEmpty())
            throw new StudentNotFoundException(new ErrorMessage("404","No students with ID: " + id + " was found",
                    "/students/" + id));


        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("lastname")
    @GET
    public Response getStudent(@QueryParam("lastname") String lastName){

        List<Student> foundStudent = studentService.findStudentByLastName(lastName);

        if (foundStudent.isEmpty())
                throw new StudentNotFoundException(new ErrorMessage(
                                "404",
                                "No students with lastname: " + lastName + " was found",
                                "/students/" + lastName));

        return Response.ok(foundStudent).build();
    }
    @Path("")
    @GET
    public Response getAllStudents(@QueryParam("students") List<String> students) {

        List<Student> foundStudent = studentService.getAllStudents();

        if (foundStudent.isEmpty())
            throw new StudentNotFoundException(new ErrorMessage(
                    "404",
                    "No students was found",
                    "/students"));

        return Response.ok(foundStudent).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {

        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        studentService.deleteStudent(id);

        return Response.ok(foundStudent).build();
    }
}
