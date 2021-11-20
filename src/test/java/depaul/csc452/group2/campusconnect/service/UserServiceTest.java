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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private StudentRepository studentRepository;

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
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), List.of(new Role("ROLE_USER")));

        given(userRepository.existsByEmail(email)).willReturn(false);

//        when(new User(anyString(), anyString(), anyString(),anyString(), any())).thenReturn(user);
//
        userService.save(dto, "ROLE_USER");
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser, user);
    }

    @Test
    void throwWhenEmailDoesNotExist() {
        String email = "jingyu120@gmail.com";
        try {
            UserRegistrationDto dto = new UserRegistrationDto("Justin", "Zhang", email, "password");
            given(userRepository.existsByEmail(email)).willReturn(true);
            userService.save(dto, "ROLE_USER");
            fail("exception should have been thrown");
        } catch (UserAlreadyExistException e) {
            assertEquals(e.getMessage(), "User already exist with email: " + email);
        }
    }


    @Test
    void checkLoadUserByUsername() {
        String userName = "jingyu120@gmail.com";
        User user = new User("Justin", "Zhang", userName, "password", List.of(new Role("ROLE_ADMIN")));
        given(userRepository.findByEmail(userName)).willReturn(user);
        UserDetails expectedDetail = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                userService.mapRolesToAuthorities(user.getRoles()));
        UserDetails userDetails = userService.loadUserByUsername(userName);
        assertEquals(expectedDetail, userDetails);

    }

    @Test
    void throwWhenUserDoesNotExist() {
        String userName = "jingyu120@gmail.com";
        try {
            given(userRepository.findByEmail(userName)).willReturn(null);
            userService.loadUserByUsername(userName);
            fail("exception should have been thrown");
        }catch (UsernameNotFoundException e) {
            assertEquals(e.getMessage(), "Invalid username or password.");
        }
    }

    @Test
    void checkMapRolesToAuthorities() {
        Collection<Role> roles = List.of(new Role("ROLE_ADMIN"));
        Collection<? extends GrantedAuthority> expected = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        Collection<? extends GrantedAuthority> grantedAuthorities = userService.mapRolesToAuthorities(roles);
        assertEquals(grantedAuthorities, expected);

    }




}