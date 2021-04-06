package de.dh.lhind.demo.jobweb.models.common;

import de.dh.lhind.demo.jobweb.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDependentModel extends BaseClass {

    private User createdBy;
    private User updatedBy;
}
