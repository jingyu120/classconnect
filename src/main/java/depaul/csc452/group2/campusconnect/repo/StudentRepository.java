package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>  {
}
