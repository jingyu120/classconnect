package depaul.csc452.group2.campusconnect.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dept;
    private int num;
    @Column(name = "nm")
    private String name;
    private String descrip;

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
    // "student_id", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "course_id", referencedColumnName = "id"))
    // @ToString.Exclude
    // private Set<Student> students;

    // public void addStudent(Student student) {
    // students.add(student);
    // student.setCourses(this);
    // }

    // public void removeStudent(Student student) {
    // students.remove(student);
    // student.setCourses(null);
    // }
}