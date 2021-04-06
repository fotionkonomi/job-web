package de.dh.lhind.demo.jobweb.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import de.dh.lhind.demo.jobweb.models.common.UserDependentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends UserDependentModel {

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	private List<Job> jobs = new ArrayList<>();
}
