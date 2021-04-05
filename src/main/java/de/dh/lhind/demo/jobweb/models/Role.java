package de.dh.lhind.demo.jobweb.models;

import javax.validation.constraints.NotNull;

import de.dh.lhind.demo.jobweb.models.common.BaseClass;
import de.dh.lhind.demo.jobweb.models.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseClass {

	@NotNull
    private RoleEnum role;
}
