package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.BadRequestException;
import depaul.csc452.group2.campusconnect.exceptions.StudentNotFoundException;
import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        studentService.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void checkFindByEmailInvokedByService() {
        String email = "admin@gmail.com";
        studentService.findStudentByEmail(email);
        verify(studentRepository).findByEmail(email);
    }

    @Test
    void checkSaveStudent() {
        String email = "jingyu120@gmail.com";
        Student student = new Student("Justin", email, List.of(), "MALE");

        studentService.saveStudent(student);
        verify(studentRepository).save(student);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(capturedStudent, student);
    }

    @Test
    void canAddStudent() {
        Student student = new Student("Justin", "jingyu120@gmail.com", List.of(), "MALE");
        studentService.addStudent(student);
        verify(studentRepository).save(student);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(capturedStudent, student);
    }


    @Test
    void willThrowWhenEmailTaken() {
        String email = "jingyu120@gmail.com";
        Student student = new Student("Justin", email, List.of(), "MALE");
        given(studentRepository.existsByEmail(anyString()))
                .willReturn(true);
        assertThatThrownBy(() -> studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + email + " is taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudent() {
        String id = "testing";
        given(studentRepository.existsById(id))
                .willReturn(true);
        studentService.deleteStudent(id);
        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        String id = "testing";
        given(studentRepository.existsById(id))
                .willReturn(false);

        assertThatThrownBy(() -> studentService.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + id + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canFindStudentByID() {
        String id = "testing";
        String email = "jingyu120@gmail.com";
        Student student = new Student("Justin", email, List.of(), "MALE");
        given(studentRepository.findById(id)).willReturn(Optional.of(student));
        studentService.getStudentByID(id);
        verify(studentRepository).findById(id);
    }

    @Test
    void throwWhenStudentNotFoundByID() {
        String id = "testing";
        given(studentRepository.findById(id))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentByID(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student not found for ID:" + id);
    }

    @Test
    void canDeleteCourseByID() {
        String email = "jingyu120@gmail.com";
        Course course = new Course(1L, "csc", 101, "intro to programming", "introduction");
        Student student = new Student("Justin", email, List.of(course), "MALE");

        given(studentRepository.findByEmail(email)).willReturn(student);
        Collection<Course> courses = student.getCourses();
        assertTrue(courses.contains(course));
        List<Course> filteredCourses = courses.stream().filter(c -> c.getId() != course.getId()).collect(Collectors.toList());
        assertTrue(filteredCourses.isEmpty());
        student.setCourses(filteredCourses);
        assertEquals(filteredCourses, student.getCourses());
        studentService.deleteCourseByID(course.getId(), email);
        verify(studentRepository).findByEmail(anyString());
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(capturedStudent, student);
    }
}