package depaul.csc452.group2.campusconnect.controller;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ModelAndView showStudents() {
        ModelAndView mv = new ModelAndView("student_table");
        mv.addObject("students", studentService.getAllStudents());
        return mv;
    }

    @GetMapping("/add")
    public String viewAddStudent(Student student) {
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/update")
    public String saveStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String showEditStudent(@PathVariable(value = "id") String id, Model model) {
        Student student = studentService.getStudentByID(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") String id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
