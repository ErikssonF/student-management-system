package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class TeacherService {


    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher){ entityManager.persist(teacher);}

    public void updateTeacher(Teacher teacher) {entityManager.merge(teacher);}

    public Optional<Teacher> getTeacherById(Long id) {
        return Optional.ofNullable(entityManager.find(Teacher.class, id));}

    public List<Teacher> findTeacherByLastName(String lastName) {

        return entityManager.createQuery("SELECT s FROM Teacher s WHERE s.lastName = :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }
    public List<Teacher> getAllTeachers(){
        return entityManager.createQuery("SELECT s FROM Teacher s", Teacher.class).getResultList();
    }

    public void deleteTeacher(Long id){
        Teacher foundTeacher = entityManager.find(Teacher.class,id);
        entityManager.remove(foundTeacher);
    }
    public Subject addSubjectToTeacher(Long id, Subject subject) {
        entityManager.find(Teacher.class, id).addSubject(subject);
        return subject;
    }
}