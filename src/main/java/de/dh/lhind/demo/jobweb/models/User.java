package de.dh.lhind.demo.jobweb.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String password;

    @NotNull
    private Role role;
}
