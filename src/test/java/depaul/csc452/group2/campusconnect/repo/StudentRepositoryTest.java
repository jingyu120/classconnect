package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.Student;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;

    @Test
    void checkSelectByEmail() {
        //given
        String email = "jingyu120@gmail.com";
        Student student = new Student("Justin", email, List.of(), "MALE");
        underTest.save(student);
        //when
        Student resultStudent = underTest.findByEmail(email);

        //then
        assertEquals(student.getEmail(), resultStudent.getEmail());
    }

    @Test
    void checkNotExistsByEmail() {
        String email = "jamila@gmail.com";
        boolean expected = underTest.existsByEmail(email);
        assertThat(expected).isFalse();

    }

    @Test
    void checkExistByEmail() {
        String email = "jingyu120@gmail.com";
        Student student = new Student("Justin", email, List.of(), "MALE");
        underTest.save(student);
        boolean expected = underTest.existsByEmail(email);
        assertThat(expected).isTrue();
    }

    @AfterEach
    void clearRepository() {
        underTest.deleteAll();
    }
}