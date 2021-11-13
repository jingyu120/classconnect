package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.model.User;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import depaul.csc452.group2.campusconnect.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;
    // public StudentService(StudentRepository studentRepository) {
    // this.studentRepository = studentRepository;

    // }

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
}
