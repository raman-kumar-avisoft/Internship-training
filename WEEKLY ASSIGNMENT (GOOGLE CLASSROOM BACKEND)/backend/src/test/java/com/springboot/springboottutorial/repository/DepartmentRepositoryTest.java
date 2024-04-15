package com.springboot.springboottutorial.repository;

import com.springboot.springboottutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("Mechanical").departmentAddress("Delhi").departmentCode("ME-01").build();

        testEntityManager.persist(department);
    }

    @Test
    public void whenFindByIdThenReturnDepartment(){
        Department department = departmentRepository.findById(1l).get();
        assertEquals(department.getDepartmentName(), "Mechanical");
    }


}