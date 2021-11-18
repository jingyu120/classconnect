package depaul.csc452.group2.campusconnect.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.springframework.data.mongodb.core.mapping.Document;

import antlr.collections.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "students")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Email(message = "not valid email address format")
    private String email;

    private String major;

    private String gender;

    private String phone_number;

    private String address;

    private Collection<Course> courses;

    public Student(String name, String email, Collection<Course> courses, String gender) {
        this.name = name;
        this.email = email;
        this.courses = courses;
        this.gender = gender;
    }

}
