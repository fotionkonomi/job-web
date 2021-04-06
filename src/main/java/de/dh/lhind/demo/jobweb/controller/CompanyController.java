package de.dh.lhind.demo.jobweb.controller;

import de.dh.lhind.demo.jobweb.models.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController<Company> {

    @ModelAttribute(name = "company")
    public Company company() {
        return new Company();
    }

    @GetMapping("/add")
    public String add(Model model) {
        return super.addPage(model);
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute("company") @Valid Company company, Errors errors) {
        return super.add(company, errors);
    }
}
