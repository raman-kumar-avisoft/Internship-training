package com.springboot.springboottutorial.repository;

import com.springboot.springboottutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// here we won't be making implementation class rather we will be extending jpaRepository class because jpa Repository gives us lot of different method for interacting our entities with our database.
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> { // parameters are entity and primary key type.

    // as there is no predefined method we have to create our own.
    public Department findByDepartmentName(String departmentName);
    // to ignore case
    public Department findByDepartmentNameIgnoreCase(String departmentName);

}
