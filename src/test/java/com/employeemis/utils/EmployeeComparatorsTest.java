package com.employeemis.utils;

import com.employeemis.models.Department;
import com.employeemis.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeComparatorsTest {
  private List<Employee<Integer>> employees;

  @BeforeEach
  void setU() {
    Department<Integer> dept = new Department<>("HR");
    employees = new ArrayList<>(List.of(
      new Employee<>(1, "e1", dept, 100, 4, 1.3),
      new Employee<>(2, "e2", dept, 100, 7, 3.3),
      new Employee<>(3, "e3", dept, 100, 2, 3.1)
    ));
  }

  @AfterEach
  void tearDown() {
    employees = null;
  }

  @Test
  @DisplayName("Should sort by experience in descending order")
  void byExperienceDesc() {
    List<Employee<Integer>> employeeList = employees.stream()
      .sorted(EmployeeComparators.byExperienceDesc()).collect(Collectors.toList());
    assertEquals(List.of(employees.get(1), employees.get(0), employees.get(2)), employeeList);
  }

  @Test
  @DisplayName("Should sort by performanceRate in descending order")
  void byPerformanceDesc() {
    List<Employee<Integer>> employeeList = employees.stream()
      .sorted(EmployeeComparators.byPerformanceDesc()).collect(Collectors.toList());
    assertEquals(List.of(employees.get(1), employees.get(2), employees.get(0)), employeeList);
  }
}