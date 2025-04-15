package com.employeemis.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Department extends Entity {
  private String name;
  private final Set<Employee> employees;

  public Department(String name) {
    super();
    setName(name);

    employees = new HashSet<>();
  }

  public void addEmployee(Employee employee) {
    if (employees.add(employee))
      employee.setDepartment(this);
  }

  public void removeEmployee(Employee employee) {
    employees.remove(employee);
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
