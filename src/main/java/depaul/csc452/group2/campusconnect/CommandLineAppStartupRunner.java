package depaul.csc452.group2.campusconnect;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import depaul.csc452.group2.campusconnect.model.Role;
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
        User user = userRepository.findByEmail("admin@gmail.com");
        if (user == null) {
            UserRegistrationDto dto = new UserRegistrationDto("admin", "admin", "admin@gmail.com", "password");
            userService.saveAdmin(dto);
        }

    }
}