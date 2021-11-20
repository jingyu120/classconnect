package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Role;
import depaul.csc452.group2.campusconnect.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        String email = "email";
        User user = new User("Justin", "Zhang", email, "123", List.of(new Role("ROLE_ADMIN")));
        userRepository.save(user);
        User result = userRepository.findByEmail(email);
        assertEquals(user, result);
    }
}