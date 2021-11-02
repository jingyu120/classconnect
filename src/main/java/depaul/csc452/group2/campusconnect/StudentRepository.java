package depaul.csc452.group2.campusconnect;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>  {
}
