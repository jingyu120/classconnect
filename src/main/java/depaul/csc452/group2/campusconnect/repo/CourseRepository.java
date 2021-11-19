package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>  {
}
