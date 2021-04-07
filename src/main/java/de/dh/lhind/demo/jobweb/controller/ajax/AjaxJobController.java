package de.dh.lhind.demo.jobweb.controller.ajax;

import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/apply/{id}")
    public void applyForJob(@PathVariable("id") Long id) {
        HttpEntity<Long> body = new HttpEntity<>(id);
        restCaller.putExchange(endpoint.getApplyForJobEndpoint(), body, Job.class);
    }
}
