package de.dh.lhind.demo.jobweb.controller.util.converter;

import de.dh.lhind.demo.jobweb.models.Role;
import de.dh.lhind.demo.jobweb.models.enums.RoleEnum;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String source) {
        if(EnumUtils.isValidEnum(RoleEnum.class, source)) {
            for(RoleEnum roleEnum : RoleEnum.values()) {
                if(roleEnum.name().equals(source)) {
                    return new Role(roleEnum);
                }
            }
        }
        return null;
    }
}
