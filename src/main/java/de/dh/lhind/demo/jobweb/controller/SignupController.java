package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.models.User;
import de.dh.lhind.demo.jobweb.models.enums.RoleEnum;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class SignupController extends AbstractController<User>{

    @Autowired
    private RestCaller restCaller;

    public SignupController() {
        super(User.class);
    }

    @ModelAttribute("newUser")
    public User newUser() {
        return new User();
    }

    @ModelAttribute("roles")
    public RoleEnum[] roles() {
        return RoleEnum.values();
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return super.addPage(model);
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("newUser") @Valid User user, Errors errors) {
        return super.add(user, errors, false);
    }


}
