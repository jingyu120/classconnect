package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.UserAlreadyExistException;
import depaul.csc452.group2.campusconnect.model.Role;
import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;
import depaul.csc452.group2.campusconnect.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User save(UserRegistrationDto registrationDto, String role) {
        User existUser = userRepository.findByEmail(registrationDto.getEmail());
        if (existUser != null) {
            throw new UserAlreadyExistException("User already exist with email: ");
        }
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role(role)));
        String name = registrationDto.getFirstName() + " " + registrationDto.getLastName();
        Student student = new Student(name, registrationDto.getEmail(), Arrays.asList(), role);
        studentRepository.save(student);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
