package depaul.csc452.group2.campusconnect;


import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.StudentRepositoryTest;
import depaul.csc452.group2.campusconnect.service.CourseServiceTest;
import depaul.csc452.group2.campusconnect.service.StudentServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        StudentRepositoryTest.class
//        CourseServiceTest.class,
//        StudentServiceTest.class
})
public class TestSuite {
}