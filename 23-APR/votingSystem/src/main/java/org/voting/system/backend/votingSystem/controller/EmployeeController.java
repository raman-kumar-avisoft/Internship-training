package org.voting.system.backend.votingSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.voting.system.backend.votingSystem.dao.EmployeeDAO;
import org.voting.system.backend.votingSystem.entity.Election;
import org.voting.system.backend.votingSystem.entity.Employee;
import org.voting.system.backend.votingSystem.error.EmployeeNotFoundException;
import org.voting.system.backend.votingSystem.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeService employeeService;

    // EMPLOYEE CLASS METHODS
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
//        return employeeDAO.findAll(employee);
        return employeeService.fetchEmployeeList();
    }
    @GetMapping("/employee/{id}")
    public Employee getEmployeesByEmployeeId(@PathVariable("id") Long id2) throws EmployeeNotFoundException {
//        return employeeDAO.findAll(employee);
        return employeeService.fetchEmployeeById(id2);
    }
    @GetMapping("/employee/name/{profile}")
    public List<Employee> fetchEmployeeByProfile(@PathVariable("profile") String profile2) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeByProfile(profile2);
    }
    @PostMapping("/addEmployee")
    public Employee postEmployee(@Valid @RequestBody Employee employee){
//        return employeeDAO.save(employee);
        return employeeService.addEmployee(employee);
    }
    @DeleteMapping("/removeEmployee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id2){
        employeeService.deleteEmployeeById(id2);
    }
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long employeeId,
                                       @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }


    // ELECTION CLASS METHODS
    @GetMapping("/elections")
    public List<Election> getElection(){
        return employeeService.fetchElectionList();
    }
    @PostMapping("/addElection")
    public Election postElection(@Valid @RequestBody Election election){
        return employeeService.addElection(election);
    }
    @DeleteMapping("/removeElection/{id}")
    public void deleteElection(@PathVariable("id") Long id2){
        employeeService.deleteElectionById(id2);
    }
}