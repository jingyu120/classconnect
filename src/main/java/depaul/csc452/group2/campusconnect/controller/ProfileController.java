package depaul.csc452.group2.campusconnect.controller;

import depaul.csc452.group2.campusconnect.model.UserProfile;
import depaul.csc452.group2.campusconnect.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.sql.Date;

@RestController

public class ProfileController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("profile")
    public ModelAndView showStudents() {
        ModelAndView mv = new ModelAndView("profile");
        UserProfile profile = new UserProfile("jingyu120", "Justin Zhang", "jingyu120@gmail.com", "1407 w huron st", "Vanessa", "2485508348", "male", Date.valueOf(LocalDate.now()));
        mv.addObject("user", profile);
        return mv;
    }
}
