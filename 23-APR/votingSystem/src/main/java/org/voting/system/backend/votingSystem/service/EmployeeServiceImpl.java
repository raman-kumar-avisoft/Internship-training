package org.voting.system.backend.votingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voting.system.backend.votingSystem.entity.Election;
import org.voting.system.backend.votingSystem.entity.Employee;
import org.voting.system.backend.votingSystem.error.EmployeeNotFoundException;
import org.voting.system.backend.votingSystem.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }
    public Employee fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        Optional<Employee> department = employeeRepository.findById(employeeId);
        if(!department.isPresent()){
            throw new EmployeeNotFoundException("Department Not Available");
        }
        return department.get();
    }

    public List<Employee> fetchEmployeeByProfile(String profile) {
        return employeeRepository.findAllByEmployeeProfile(profile);
    }

    public void deleteEmployeeById(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }


    @Override
    public List<Election> fetchElectionList() {
        return null;
    }
    @Override
    public Election addElection(Election election) {
        return null;
    }
    @Override
    public void deleteElectionById(Long electionId) {

    }


    public Employee updateEmployee(Long employeeId, Employee employee) {
        Employee empDB = employeeRepository.findById(employeeId).get();

        if(Objects.nonNull(employee.getEmployeeName()) &&
                !"".equalsIgnoreCase(employee.getEmployeeName())){
            empDB.setEmployeeName(employee.getEmployeeName());
        }

        if(Objects.nonNull(employee.getEmployeeProfile()) &&
                !"".equalsIgnoreCase(employee.getEmployeeProfile())){
            empDB.setEmployeeProfile(employee.getEmployeeProfile());
        }

        if(Objects.nonNull(employee.getEmployeeUserName()) &&
                !"".equalsIgnoreCase(employee.getEmployeeUserName())){
            empDB.setEmployeeUserName(employee.getEmployeeUserName());
        }

        if(Objects.nonNull(employee.getEmployeePassword()) &&
                !"".equalsIgnoreCase(employee.getEmployeePassword())){
            empDB.setEmployeeUserName(employee.getEmployeePassword());
        }

        return employeeRepository.save(empDB);
    }
}
