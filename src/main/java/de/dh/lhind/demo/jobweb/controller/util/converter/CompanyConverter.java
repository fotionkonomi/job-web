package de.dh.lhind.demo.jobweb.controller.util.converter;

import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.Company;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class CompanyConverter implements Converter<String, Company> {

    @Autowired
    private RestCaller restCaller;

    @Autowired
    private Endpoint endpoint;

    @Override
    public Company convert(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            ResponseEntity<Company> responseEntity = restCaller.getExchange(endpoint.getListOfCompaniesEndpoint() + "/" + source, Company.class);
            return responseEntity.getBody();
        } catch(HttpStatusCodeException e) {
            return null;
        }
    }
}
