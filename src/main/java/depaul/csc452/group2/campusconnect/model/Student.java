package depaul.csc452.group2.campusconnect.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "Students")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "usr_id")
    private String userID;

    @Column(name = "nm")
    private String name;

    @Email(message = "not valid email address format")
    private String email;

    private String major;

    private String minor;

    private String admittedDate;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private Set<Course> courses;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
