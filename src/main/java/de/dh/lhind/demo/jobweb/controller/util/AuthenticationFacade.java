package de.dh.lhind.demo.jobweb.controller.util;

import de.dh.lhind.demo.jobweb.models.User;

public interface AuthenticationFacade {
    User getAuthenticatedUser();

    String getToken();
}
