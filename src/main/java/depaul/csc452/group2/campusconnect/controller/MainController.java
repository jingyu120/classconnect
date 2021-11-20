package depaul.csc452.group2.campusconnect.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.StudentService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String home(Model model, Principal principal) {
        Student student = studentService.findStudentByEmail(principal.getName());
        model.addAttribute("courses", student.getCourses());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourseFromStudent(@PathVariable(value = "id") long id, Principal principal) {
        studentService.deleteCourseByID(id, principal.getName());
        return "redirect:/";
    }
}
