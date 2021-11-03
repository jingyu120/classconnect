package depaul.csc452.group2.campusconnect.controller;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.ICourseService;
import depaul.csc452.group2.campusconnect.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ModelAndView showCourses() {
        ModelAndView mv = new ModelAndView("courses");
        mv.addObject("courses", courseService.findAll());
        return mv;
    }
}
