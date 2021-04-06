package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.User;
import de.dh.lhind.demo.jobweb.models.enums.RoleEnum;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestOperations;

import javax.validation.Valid;

@Controller
@Log4j
public class SignupController {

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private Endpoint endpoint;

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
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("newUser") @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getArguments() + error.getDefaultMessage()));
            return "signup";
        }
        restOperations.exchange(endpoint.getRemoteRootUri() + endpoint.getSignupEndpoint(), HttpMethod.POST, new HttpEntity<>(user), Void.class);

        return "redirect:/";
    }


}
