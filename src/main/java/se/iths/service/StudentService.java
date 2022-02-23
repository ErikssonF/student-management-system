package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class StudentService {


    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student){ entityManager.persist(student);}

    public void updateStudent(Student student) {entityManager.merge(student);}

    public Optional<Student> getStudentById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));}

    public List<Student> findStudentByLastName(String lastName) {

        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }
    public List<Student> getAllStudents(){
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public void deleteStudent(Long id){
        Student foundStudent = entityManager.find(Student.class,id);
        entityManager.remove(foundStudent);
    }
}
