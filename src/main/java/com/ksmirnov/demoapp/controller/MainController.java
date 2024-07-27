package com.ksmirnov.demoapp.controller;

import com.ksmirnov.demoapp.model.Customer;
import com.ksmirnov.demoapp.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Value("${app.student.countries}")
    private List<String> studentCountries;
    @Value("${app.student.languages}")
    private List<String> studentLanguages;
    @Value("${app.student.systems}")
    private List<String> studentSystems;

    @GetMapping("/showFormNoObject")
    public String showFormNoObject() {
        return "input-form-no-object";
    }

    @PostMapping("/processFormNoObject")
    public String processFormNoObject(HttpServletRequest request, @RequestParam("userName") String reqUserName, Model model) {

        String userName = request.getParameter("userName").toUpperCase();
        String message1 = userName + ": this parameter is retrieved by using a 'HttpServletRequest' object";
        model.addAttribute("message1", message1);

        String message2 = reqUserName.toUpperCase() + ": this parameter is retrieved by using the '@RequestParam' annotation";
        model.addAttribute("message2", message2);

        return "process-form-no-object";
    }

    @GetMapping("/showFormStudent")
    public String showFormStudent(Model model) {

        Student student = new Student();

        model.addAttribute("student", student);
        model.addAttribute("countries", studentCountries);
        model.addAttribute("languages", studentLanguages);
        model.addAttribute("systems", studentSystems);

        return "input-form-student";
    }

    @PostMapping("/processFormStudent")
    public String processFormStudent(@ModelAttribute("student") Student student) {

        System.out.println("student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("country: " + student.getCountry());
        System.out.println("favorite language: " + student.getFavoriteLanguage());
        System.out.println("favorite system: " + student.getFavoriteSystems());

        return "process-form-student";
    }

    @GetMapping("/showFormCustomer")
    public String showFormCustomer(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "input-form-customer";
    }

    @PostMapping("/processFormCustomer")
    public String processFormCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "input-form-customer";
        }
        System.out.println("customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("free passes: " + customer.getFreePasses());
        System.out.println("postal code: " + customer.getPostalCode());
        System.out.println("course code: " + customer.getCourseCode());

        return "process-form-customer";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
