package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String subjectName;
    @ManyToMany
    private Set<Student> students = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT",
    joinColumns = @JoinColumn(name = "subject", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "STUDENT_ID",referencedColumnName = "id"))
    private Teacher teacher;



    public void addStudent(Student student){
        students.add(student);
        student.addSubject(this);
    }

    public void removeStudent(Student student){
        students.remove(student);
        student.setSubject(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
