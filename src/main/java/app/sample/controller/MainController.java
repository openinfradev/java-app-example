package app.sample.controller;

import app.sample.repository.User;
import app.sample.service.CommonService;
import app.sample.service.UserService;
import app.sample.value.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Value("${spring.style.color}")
    private String style;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView) throws Exception {

        commonService.syncContainer();
        List<Container> containers = commonService.getContainers();

        List<User> users = userService.getUsers();

        modelAndView.addObject("myIp", commonService.getMyIp());
        modelAndView.addObject("containers", containers);
        modelAndView.addObject("users", users);
        modelAndView.addObject("style", style);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
