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
    @JoinTable(name = "STUDENT_SUBJECT",
            joinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "id"))
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "id")
    private Teacher teacher;

    public void addStudent(Student student) {
        students.add(student);
        student.addSubject(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setSubjects(null);
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

    public Set<Student> getStudent() {
        return students;
    }

    public void setStudent(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}