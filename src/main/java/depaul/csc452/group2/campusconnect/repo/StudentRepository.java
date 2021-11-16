package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByEmail(String userEmail);
}
