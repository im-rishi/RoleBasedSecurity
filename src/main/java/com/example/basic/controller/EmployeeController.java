package com.example.basic.controller;

import com.example.basic.entity.Employee;
import com.example.basic.service.employeeservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final employeeservice employeeservice;
    public String currentUser()
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        return loggedInUser.getName();
    }

    public EmployeeController(employeeservice employeeservice){
        super();
        this.employeeservice = employeeservice;
    }
    @GetMapping("/")
    public String listEmployees(Model model,String keyword){
        model.addAttribute("employees",employeeservice.getAllemployees());
        model.addAttribute("User",currentUser());
        logger.trace("Home Page Accessed");
        return "employees";

    }

    @GetMapping("/employee/new")
    public String CreateEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        logger.trace("Create page Accessed");
        return "Create_employee";
    }
    @PostMapping("/employees")
    public String saveStudent(@ModelAttribute("employee") Employee employee) {
        employeeservice.saveStudent(employee);
        return "redirect:/";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeservice.getEmployeebyId(id));
        logger.trace("update page accessed");
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @ModelAttribute("employee") Employee employee) {

        Employee existingEmployee = employeeservice.getEmployeebyId(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeservice.updateEmployee(existingEmployee);
        return "redirect:/";
    }

    @GetMapping("/employees/remove/{id}")
    public String DeleteEmployee(@PathVariable Long id){
        employeeservice.DeleteEmployee(id);
        logger.trace("Successfully deleted");
        return "redirect:/";

    }
    @GetMapping("/search")
    public String home(Employee employee, Model model, String keyword) {
        List<Employee> employees;
        employees = employeeservice.getByKeyword(keyword);
        model.addAttribute("employees", employees);
        model.addAttribute("user",currentUser());
        return "employees";
    }
/*
    @GetMapping("/admin")
    public String admin()
    {
        return "redirect:/";
    }

    @GetMapping("/adminModified")
    public String listNewEmployees(Model model){
        model.addAttribute("employees",employeeservice.getAllemployees());
        logger.trace("Home Page Accessed");
        return "employees";

    }
    @GetMapping("/user")
    public String user(){
        return "redirect:/";
    }
    @GetMapping("/userModified")
    public String listNewUserEmployees(Model model) {
        model.addAttribute("employees", employeeservice.getAllemployees());
        logger.trace("Home Page Accessed");
        return "User_employees";
    }
    @GetMapping("/403")
    public String error(){
        return "403";
    }
*/
}
