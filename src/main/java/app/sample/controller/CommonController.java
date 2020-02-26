package app.sample.controller;

import app.sample.repository.User;
import app.sample.value.Container;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CommonController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public Object health() throws Exception {
        return "OK";
    }
} 