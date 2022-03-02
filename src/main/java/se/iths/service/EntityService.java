package se.iths.service;

import se.iths.dao.EntityDao;
import se.iths.entity.Student;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class EntityService<T> implements EntityDao<T> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(T t) {
        entityManager.persist(t);
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(T t) {

    }

    public List<Student> getStudentByLastName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<Teacher> getTeacherByLastname(String lastName) {
        return entityManager.createQuery("SELECT t FROM Teacher t WHERE t.lastName = :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getResultList();

    }
}