package de.dh.lhind.demo.jobweb.security;

import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.Role;
import de.dh.lhind.demo.jobweb.models.User;
import de.dh.lhind.demo.jobweb.models.authentication.AuthenticationRequest;
import de.dh.lhind.demo.jobweb.models.authentication.AuthenticationResponse;
import de.dh.lhind.demo.jobweb.models.common.UserDependentModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private Endpoint endpoint;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = null;

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthenticationRequest authRequest = new AuthenticationRequest(email, password);
        ResponseEntity<AuthenticationResponse> authResponse = restOperations.postForEntity(endpoint.getRemoteRootUri() + endpoint.getAuthenticationEndpoint(), new HttpEntity<>(authRequest), AuthenticationResponse.class);
        if (authResponse.getStatusCode() == HttpStatus.OK) {
            String jwt = authResponse.getBody().getJwt();
            if (!StringUtils.isEmpty(jwt)) {
                HttpHeaders headers = new HttpHeaders();
                headers.set("authorization", "Bearer " + jwt);
                ResponseEntity<User> userResponse = restOperations.postForEntity(endpoint.getRemoteRootUri() + endpoint.getFindUserByEmailEndpoint(), new HttpEntity<>(email, headers), User.class);
                User user = userResponse.getBody();
                user.setToken(jwt);
                Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
                authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, password, grantedAuthorities);
            }
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }

        return authenticationToken;
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role roleObject = user.getRole();
        if(roleObject != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleObject.getRole().name()));
        }
        return grantedAuthorities;
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
