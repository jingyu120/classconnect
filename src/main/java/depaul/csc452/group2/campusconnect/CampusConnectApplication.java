package depaul.csc452.group2.campusconnect;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.UserProfile;
import depaul.csc452.group2.campusconnect.repo.UserProfileRepository;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication

public class CampusConnectApplication {
    // private static final Logger log = LoggerFactory.getLogger(CampusConnectApplication.class);

//     //
//     @Bean
//     public CommandLineRunner saveUserProfile(UserProfileRepository repository) {
//         return (args) -> {
// //            repository.deleteAll();
//             UserProfile profile = new UserProfile();
//             profile.setUserID("jingyu120");
//             profile.setEmail("myemail@gmail.com");
//             profile.setGender("male");
//             repository.save(profile);

//             UserProfile profile2 = new UserProfile();
//             profile2.setUserID("userID123");
//             profile2.setEmail("goldentanchy@gmail.com");
//             profile2.setGender("female");
//             repository.save(profile2);

//             List<UserProfile> profiles = repository.findAll();
//             for (UserProfile p : profiles) {
//                 log.info(p.toString());
//                 System.out.println(p);
//             }
//         };
//     }

// 	@Bean
// 	public CommandLineRunner saveStudent(StudentRepository repository) {
// 		return (args) -> {
// 			Student s1 = new Student();
// 			s1.setName("JZ");
// 			s1.setMajor("Computer Science");
// 			repository.save(s1);

// 			Student s2 = new Student();
// 			s2.setName("Vanessa");
// 			s2.setMajor("Civil Engineering");
// 			repository.save(s2);

// 			List<Student> students = repository.findAll();
// 			for (Student s : students) {
// 				log.info(s.toString());
// 				System.out.println(s);
// 			}
// 		};
// 	}

    public static void main(String[] args) {
        SpringApplication.run(CampusConnectApplication.class, args);
    }
}
