package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.controller.util.PageUtil;
import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.models.common.BaseClass;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

@Log4j
public abstract class AbstractController<T extends BaseClass> {

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    protected RestCaller restCaller;

    public String addPage(Model model) {
        String page = pageUtil.getAddPage(this.getClass());
        return page;
    }

    public String add(T model, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            String addPage =  pageUtil.getAddPage(this.getClass());
            return addPage;
        }

        HttpEntity<T> body = new HttpEntity<>(model);

        HttpEntity<Void> response = restCaller.postExchange("/" + pageUtil.getContextPage(this.getClass()), body, Void.class);
        HttpHeaders header = response.getHeaders();
        log.info("Location: " + header.getLocation());
        return "redirect:/" + pageUtil.getContextPage(this.getClass()) + "/list";

    }

}
