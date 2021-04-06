package de.dh.lhind.demo.jobweb.models;

import de.dh.lhind.demo.jobweb.models.common.UserDependentModel;
import de.dh.lhind.demo.jobweb.models.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Job extends UserDependentModel {

	@Min(value = 0)
	@NotNull
	private Double minYearsOfExperience;

	@NotNull
	private Company hiringCompany;

	@Min(value = 0)
	private Integer wage;

	@NotEmpty
	private String jobTitle;

	@NotEmpty
	private String description;

	@NotNull
	private JobType jobType;
}
