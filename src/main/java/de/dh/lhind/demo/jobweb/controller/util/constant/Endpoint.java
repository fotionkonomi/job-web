package de.dh.lhind.demo.jobweb.controller.util.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Endpoint {

    @Value("${remote.ws.rootUri}")
    private String remoteRootUri;

    @Value("${endpoint.authentication}")
    private String authenticationEndpoint;

    @Value("${endpoint.find.user.email}")
    private String findUserByEmailEndpoint;

    @Value("${endpoint.user.register}")
    private String signupEndpoint;

    @Value("${endpoint.company.list}")
    private String listOfCompaniesEndpoint;

    @Value("${endpoint.job}")
    private String jobEndpoint;
}
