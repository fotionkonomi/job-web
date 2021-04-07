package de.dh.lhind.demo.jobweb.models;

import javax.validation.constraints.*;

import de.dh.lhind.demo.jobweb.models.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseClass implements UserDetails {
	
	@Size(max = 50)
    @NotEmpty
    private String firstName;

    @Size(max = 50)
    @NotEmpty
    private String lastName;

    @Size(max = 100)
    @NotEmpty
    @Email
    private String email;

    @Size(max = 255)
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    private String password;

    @NotNull
    private Role role;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(this.role != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(this.role.getRole().name()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
