package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
