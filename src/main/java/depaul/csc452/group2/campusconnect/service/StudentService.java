package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            student = new Student(name, userEmail, Arrays.asList());
        }

        return student;
    }

    public Student getStudentByID(String id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException("Student not found for ID:" + id);
        }
        return student;
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public void deleteCourseByID(long courseID, String email) {
        Student student = studentRepository.findByEmail(email);
        Collection<Course> courses = student.getCourses();
        List<Course> newCourses = courses.stream().filter(course -> course.getId() != courseID)
                .collect(Collectors.toList());
        student.setCourses(newCourses);
        studentRepository.save(student);

    }
}
