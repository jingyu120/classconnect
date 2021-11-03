package depaul.csc452.group2.campusconnect.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String dept;
    private int num;
    @Column(name="nm")
    private String name;
    private String descrip;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Student> students = new ArrayList<Student>();
    public void addStudent(Student student) {
        students.add(student);
        student.setCourses(this);
    }
 
    public void removeStudent(Student student) {
        students.remove(student);
        student.setCourses(null);
    }
}