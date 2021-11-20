package depaul.csc452.group2.campusconnect.controller;

import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.CourseService;
import depaul.csc452.group2.campusconnect.service.StudentService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ModelAndView showCourses() {
        ModelAndView mv = new ModelAndView("courses");
        mv.addObject("courses", courseService.findAll());
        return mv;
    }

    @GetMapping("add")
    public String showSignUpForm(Course course) {
        return "add_course";
    }

    @PostMapping
    public String add(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_course";
        }

        courseService.update(course);
        model.addAttribute("courses", courseService.findAll());
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/enroll/{id}")
    public String enrollCourse(@PathVariable(value = "id") long id, Principal principal) {
        String email = principal.getName();
        Student student = studentService.findStudentByEmail(email);
        Course course = courseService.findCourseByID(id);
        Collection<Course> courses = student.getCourses();
        if (!courses.contains(course)) {
            courses.add(course);
            studentService.saveStudent(student);
        }
        return "redirect:/courses";
    }
}
