package depaul.csc452.group2.campusconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping

public class DemoController {
    @GetMapping("login")
    public ModelAndView something () {
        ModelAndView mv = new ModelAndView("sign_in");
        return mv;
    }
}
