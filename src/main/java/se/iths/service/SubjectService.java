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

    public void createSubject(Subject subject){ entityManager.persist(subject);}

    public void updateSubject(Subject subject) {entityManager.merge(subject);}

    public Optional<Subject> getSubjectById(Long id) {
        return Optional.ofNullable(entityManager.find(Subject.class, id));}

    public List<Subject> findSubjectByLastName(String lastName) {

        return entityManager.createQuery("SELECT s FROM Subject s WHERE s.teacher = :lastName", Subject.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }
    public List<Subject> getAllSubjects(){
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public void deleteSubject(Long id){
        Subject foundSubject = entityManager.find(Subject.class,id);
        entityManager.remove(foundSubject);
    }
}