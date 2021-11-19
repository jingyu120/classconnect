package depaul.csc452.group2.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.StudentService;

import java.security.Principal;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showStudents(Model model, Principal principal) {
        String userEmail = principal.getName();
        model.addAttribute("user", studentService.findStudentByEmail(userEmail));
        return "profile";
    }

    @GetMapping("/edit/{id}")
    public String editProfile(@PathVariable(value = "id") String id, Model model) {
        Student student = studentService.getStudentByID(id);
        model.addAttribute("student", student);
        return "edit_profile";
    }

    @PostMapping
    public String saveStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/profile";
    }
}
