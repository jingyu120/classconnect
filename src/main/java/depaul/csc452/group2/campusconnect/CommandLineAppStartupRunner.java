package depaul.csc452.group2.campusconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.UserRepository;
import depaul.csc452.group2.campusconnect.service.UserService;
import depaul.csc452.group2.campusconnect.web.dto.UserRegistrationDto;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = userRepository.findByEmail("admin@gmail.com");
        if (admin == null) {
            UserRegistrationDto dto = new UserRegistrationDto("admin", "admin", "admin@gmail.com", "password");
            userService.saveAdmin(dto);
        }
        User user = userRepository.findByEmail("user@gmail.com");
        if (user == null) {
            UserRegistrationDto dto2 = new UserRegistrationDto("userFirstName", "userLastName", "user@gmail.com",
                    "password");
            userService.save(dto2);
        }
    }
}