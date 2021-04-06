package de.dh.lhind.demo.jobweb.models;

import javax.validation.constraints.*;

import de.dh.lhind.demo.jobweb.models.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseClass {
	
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
}
