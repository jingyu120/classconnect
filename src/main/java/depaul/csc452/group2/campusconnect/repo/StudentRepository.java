package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByEmail(String userEmail);
    Boolean existsByEmail(String email);

}
