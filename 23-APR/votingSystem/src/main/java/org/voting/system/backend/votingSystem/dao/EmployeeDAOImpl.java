package org.voting.system.backend.votingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.voting.system.backend.votingSystem.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Override
//    public int save(Employee employee){
//        String sql = "INSERT INTO employee_table (employee_name,  employee_profile, employee_user_name, employee_password) VALUES (?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, employee.getEmployeeName(), employee.getEmployeeProfile(), employee.getEmployeeUserName(), employee.getEmployeePassword());
//    }
//
//
//    public int update(Employee employee, int id){
//        return 0;
//    }
//
//    public int delete(Long id){
//        String sql = "DELETE FROM employee_table WHERE employee_id = ?";
//        return jdbcTemplate.update(sql, id);
//    }
//
//    public List<Employee> getAll(){
//        return jdbcTemplate.query("SELECT * FROM employee_table", new BeanPropertyRowMapper<Employee>(Employee.class));
//    }
//
//    public Employee getById(Long id){
//        String sql = "SELECT * FROM employee_table WHERE employee_id = ?";
//        return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id);
//    }

}
