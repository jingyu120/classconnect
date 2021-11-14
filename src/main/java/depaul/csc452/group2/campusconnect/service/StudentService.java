package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findStudentByEmail(String userEmail) {
        Student student = studentRepository.findByEmail(userEmail);
        if (student == null) {
            User user = userRepository.findByEmail(userEmail);
            String name = user.getFirstName() + " " + user.getLastName();
            student = new Student(name, userEmail);
        }

        return student;
    }
    public Student getStudentByID(long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException("Student not found for ID:" + id);
        }
        return student;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}
