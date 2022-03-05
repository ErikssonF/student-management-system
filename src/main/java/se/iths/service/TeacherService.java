package se.iths.service;

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

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return Optional.ofNullable(entityManager.find(Teacher.class, id));
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }

    public List<Teacher> getTeacherByLastName(String lastName) {
        return entityManager.createQuery("SELECT t FROM Teacher t WHERE t.lastName = :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }
}