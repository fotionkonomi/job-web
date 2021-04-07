package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.controller.util.PageUtil;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import de.dh.lhind.demo.jobweb.models.Company;
import de.dh.lhind.demo.jobweb.models.Job;
import de.dh.lhind.demo.jobweb.models.enums.JobType;
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
        return super.addPage(model);
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute("job") @Valid Job job, Errors errors) {
        return super.add(job, errors);
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        ResponseEntity<Job> jobResponseEntity = restCaller.getExchange(endpoint.getJobEndpoint() + "/" + id, Job.class);
        Job job = jobResponseEntity.getBody();
        model.addAttribute("job", job);
        return "job/add-job";
    }

    @PostMapping("/update/{id}")
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
        return "redirect:/" + pageUtil.getContextPage(this.getClass()) + "/list";
    }

    @GetMapping("/list")
    public String jobList(Model model) {
        ResponseEntity<Job[]> jobsResponseEntity = restCaller.getExchange(endpoint.getJobEndpoint(), Job[].class);
        Collection<Job> jobs = Arrays.asList(jobsResponseEntity.getBody());
        model.addAttribute("jobs", jobs);
        return "job/jobs";
    }

    @GetMapping("/search")
    public String jobsSearched(@RequestParam("query") String query, Model model) {
        ResponseEntity<Job[]> jobsResponseEntity = restCaller.getExchange(endpoint.getJobFilteredEndpoint() + "?query=" + query, Job[].class);
        Collection<Job> jobs = Arrays.asList(jobsResponseEntity.getBody());
        model.addAttribute("jobs", jobs);
        return "job/jobs";
    }

    @GetMapping("/topTen")
    public String topTenJobs(Model model) {
        ResponseEntity<Job[]> jobsResponseEntity = restCaller.getExchange(endpoint.getTopTenJobsEndpoint(), Job[].class);
        Collection<Job> jobs = Arrays.asList(jobsResponseEntity.getBody());
        model.addAttribute("jobs", jobs);
        return "job/jobs";
    }

    @GetMapping("/myApplications")
    public String myApplications(Model model) {
        ResponseEntity<Job[]> jobsResponseEntity = restCaller.getExchange(endpoint.getMyApplicationsEndpoint(), Job[].class);
        Collection<Job> jobs = Arrays.asList(jobsResponseEntity.getBody());
        model.addAttribute("jobs", jobs);
        return "job/myApplications";
    }
}
