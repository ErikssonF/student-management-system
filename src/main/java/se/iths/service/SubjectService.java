package se.iths.service;

import se.iths.entity.Subject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public Optional<Subject> getSubjectById(Long id) {
        return Optional.ofNullable(entityManager.find(Subject.class, id));
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public void updateSubject(Subject subject) {
        entityManager.merge(subject);
    }

    public void deleteSubject(Long id) {
        Subject foundTeacher = entityManager.find(Subject.class, id);
        entityManager.remove(foundTeacher);
    }

    public List<Subject> getSubjectByName(String subjectName) {
        return entityManager.createQuery("SELECT s FROM Subject s WHERE s.subjectName = :subjectName", Subject.class)
                .setParameter("subjectName", subjectName)
                .getResultList();
    }

}