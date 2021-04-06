package de.dh.lhind.demo.jobweb.controller.util.impl;

import de.dh.lhind.demo.jobweb.controller.util.AuthenticationFacade;
import de.dh.lhind.demo.jobweb.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getToken() {
        return getAuthenticatedUser().getToken();
    }
}
