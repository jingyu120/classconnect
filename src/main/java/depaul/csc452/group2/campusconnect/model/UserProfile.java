package depaul.csc452.group2.campusconnect.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UserProfile")
public class UserProfile {
    @Id
    private String userID;
    private String name;
    private String email;
    private String address;
    private String emergency;
    private String phoneNumber;
    private String gender;
    private Date dob;
}
