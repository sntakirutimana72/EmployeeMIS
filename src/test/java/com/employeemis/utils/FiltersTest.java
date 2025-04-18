package com.employeemis.utils;

import com.employeemis.models.Department;
import com.employeemis.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {
  @Nested
  class EmployeeFilters {
    private List<Employee<Integer>> employees;

    private Department<Integer> createDepartment(String tag) {
      return new Department<>(tag);
    }

    private Employee<Integer> createEmployee(int id, Department<Integer> dept) {
      return new Employee<>(id, "john", dept, 1, 2, 0);
    }

    @BeforeEach
    void setUp() {
      employees = new ArrayList<>();
      employees.add(createEmployee(1, createDepartment("hr")));
      employees.add(createEmployee(2, createDepartment("care")));
      employees.add(createEmployee(3, createDepartment("care")));
    }

    @AfterEach
    void tearDown() {
      employees = null;
    }

    @Test
    void expectByDepartmentToReturnEmployeesInGivenDepartment() {
      assertTrue(
        Filters.Employee.byDepartment(employees, "hr").stream()
          .allMatch(e -> e.getDepartment().getName().equalsIgnoreCase("hr"))
      );
    }

    @Test
    void expectByNameToReturnEmployeesWhoseNameContainsGivenPattern() {
      employees.get(0).setName("stan");
      employees.get(1).setName("STEVE");
      assertTrue(
        Filters.Employee.byName(employees, "st").stream()
          .allMatch(e -> e.getName().toLowerCase().contains("st"))
      );
    }

    @Test
    void withPerformanceGreaterThanOrEqualTo() {
      employees.get(2).setPerformanceRate(3.2);
      employees.get(1).setPerformanceRate(3);
      assertTrue(
        Filters.Employee.withPerformanceGreaterThanOrEqualTo(employees, 2.8).stream()
          .allMatch(e -> e.getPerformanceRate() >= 2.8)
      );
    }
  }
}