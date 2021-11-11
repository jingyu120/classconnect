package depaul.csc452.group2.campusconnect.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Students")
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

    private Date admittedDate;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private Set<Course> courses;
    
}
