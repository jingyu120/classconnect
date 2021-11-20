package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.UserAlreadyExistException;
import depaul.csc452.group2.campusconnect.model.Role;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;
import depaul.csc452.group2.campusconnect.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private User user;

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
        passwordEncoder = new BCryptPasswordEncoder();

    }

    @Test
    @Disabled
    void canSaveUser() {
        String email = "jingyu120@gmail.com";
        UserRegistrationDto dto = new UserRegistrationDto("Justin", "Zhang", email, "password");
        given(userRepository.existsByEmail(email)).willReturn(false);
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), List.of(new Role("ROLE_USER")));

        userService.save(dto);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser, user);


//
//        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
//                passwordEncoder.encode(dto.getPassword()), List.of(new Role("ROLE_USER")));
//        String name = dto.getFirstName() + " " + dto.getLastName();
//        Student student = new Student(name, dto.getEmail(), List.of(), "Not Selected");
//        userService.save(dto);
//        verify(studentRepository).save(student);
//        verify(userRepository).save(user);
    }

    @Test
    void throwWhenEmailDoesNotExist() {
        String email = "jingyu120@gmail.com";
        try {
            UserRegistrationDto dto = new UserRegistrationDto("Justin", "Zhang", email, "password");
            given(userRepository.existsByEmail(email)).willReturn(true);
            userService.save(dto);
        } catch (UserAlreadyExistException e) {
            assertEquals(e.getMessage(), "User already exist with email: " + email);
        }
    }

    @Test
    void throwWhenAdminEmailDoesNotExist() {
        String email = "jingyu120@gmail.com";
        try {
            UserRegistrationDto dto = new UserRegistrationDto("Justin", "Zhang", email, "password");
            given(userRepository.existsByEmail(email)).willReturn(true);
            userService.saveAdmin(dto);
        } catch (UserAlreadyExistException e) {
            assertEquals(e.getMessage(), "User already exist with email: " + email);
        }
    }

    @Test
    void throwWhenUserDoesNotExist() {
        String userName = "jingyu120@gmail.com";
        try {
            given(userRepository.findByEmail(userName)).willReturn(null);
            userService.loadUserByUsername(userName);
        }catch (UsernameNotFoundException e) {
            assertEquals(e.getMessage(), "Invalid username or password.");
        }

    }

    @Test
    void saveAdmin() {
    }

    @Test
    void loadUserByUsername() {
    }
}