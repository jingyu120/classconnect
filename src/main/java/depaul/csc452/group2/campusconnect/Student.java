package depaul.csc452.group2.campusconnect;
import java.sql.Date;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue
    private long studentID;


    @Column(name = "usr_id")
    private String userID;

    @Column(name = "nm")
    private String name;

    @Email(message = "not valid email address format")
    private String email;

    private String major;

    private String minor;

    private Date admittedDate;
}
