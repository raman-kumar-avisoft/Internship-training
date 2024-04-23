package org.voting.system.backend.votingSystem.service;

import org.voting.system.backend.votingSystem.entity.Election;
import org.voting.system.backend.votingSystem.entity.Employee;
import org.voting.system.backend.votingSystem.error.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee);
    public List<Employee> fetchEmployeeList();
    public Employee fetchEmployeeById (Long employeeId) throws EmployeeNotFoundException;
    public List<Employee> fetchEmployeeByProfile(String profile);
    public Employee updateEmployee(Long employeeId, Employee employee);
    public void deleteEmployeeById(Long employeeId);
    public List<Election> fetchElectionList();
    public Election addElection(Election election);
    public void deleteElectionById(Long electionId);
}
