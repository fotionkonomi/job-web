package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.controller.util.PageUtil;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.Company;
import de.dh.lhind.demo.jobweb.models.Job;
import de.dh.lhind.demo.jobweb.models.enums.JobType;
import de.dh.lhind.demo.jobweb.models.enums.RoleEnum;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/job")
@Log4j
public class JobController extends AbstractController<Job> {

    @Autowired
    private Endpoint endpoint;

    @Autowired
    private PageUtil pageUtil;

    @ModelAttribute(name = "job")
    public Job job() {
        return new Job();
    }

    @ModelAttribute("jobTypes")
    public JobType[] jobTypes() {
        return JobType.values();
    }

    @ModelAttribute(name = "companies")
    public Collection<Company> companies() {
        HttpEntity<Company[]> companies = restCaller.getExchange(endpoint.getListOfCompaniesEndpoint(), Company[].class);
        return Arrays.asList(companies.getBody());
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("method", "POST");
        return super.addPage(model);
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute("job") @Valid Job job, Errors errors) {
        return super.add(job, errors);
    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("method", "PUT");
        ResponseEntity<Job> jobResponseEntity = restCaller.getExchange(endpoint.getJobEndpoint() + "/" + id, Job.class);
        Job job = jobResponseEntity.getBody();
        model.addAttribute("job", job);
        return "job/add-job";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("job") @Valid Job job, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            String addPage =  pageUtil.getAddPage(this.getClass());
            return addPage;
        }

        HttpEntity<Job> body = new HttpEntity<>(job);

        HttpEntity<Void> response = restCaller.putExchange("/" + pageUtil.getContextPage(this.getClass()), body, Void.class);
        HttpHeaders header = response.getHeaders();
        log.info("Location: " + header.getLocation());
        return "redirect:" + header.getLocation().toString().toLowerCase();

    }

    @GetMapping("/list")
    public String jobList(Model model) {
        ResponseEntity<Job[]> jobsResponseEntity = restCaller.getExchange(endpoint.getJobEndpoint(), Job[].class);
        Collection<Job> jobs = Arrays.asList(jobsResponseEntity.getBody());
        model.addAttribute("jobs", jobs);
        return "job/jobs";
    }
}
