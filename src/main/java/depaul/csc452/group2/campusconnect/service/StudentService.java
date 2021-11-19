package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.BadRequestException;
import depaul.csc452.group2.campusconnect.exceptions.StudentNotFoundException;
import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
    public void addStudent(Student student) {
        String email = student.getEmail();
        Boolean existsEmail = studentRepository.existsByEmail(email);
        if (existsEmail) {
            throw new BadRequestException("Email " + email + " is taken.");
        }
        studentRepository.save(student);
    }

    public Student findStudentByEmail(String userEmail) {
        return studentRepository.findByEmail(userEmail);
    }

    public Student getStudentByID(String id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new StudentNotFoundException("Student not found for ID:" + id);
        }
        return student;
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student with id " + id + " does not exists");
        }
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
