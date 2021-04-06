package de.dh.lhind.demo.jobweb.controller.ajax;

import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/job")
public class AjaxJobController {

    @Autowired
    private RestCaller restCaller;

    @Autowired
    private Endpoint endpoint;

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable("id") Long id) {
        restCaller.deleteExchange(endpoint.getJobEndpoint() + "/" + id);
    }
}
