package com.employeemis.models;

import java.util.Objects;

public class Employee extends ActivableEntity implements Employable {
  // Contracted from Employable interface
  private String name;
  private Department department;
  private double salary;
  private int yearsOfExperience;
  private double performanceRate;

  public Employee(String name, Department department, double salary, int yearsOfExperience, double performanceRate) {
    super();
    setName(name);
    assignDepartment(department);
    setSalary(salary);
    setYearsOfExperience(yearsOfExperience);
    setPerformanceRate(performanceRate);
    setIsActive(true);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  private void assignDepartment(Department department) {
    this.department = department;
    department.addEmployee(this);
  }

  @Override
  public void setDepartment(Department department) {
    if (this.department == department)
      return;
    Department previous = this.department;
    assignDepartment(department);
    previous.removeEmployee(this);
  }

  @Override
  public void setSalary(double salary) {
    this.salary = salary;
  }

  @Override
  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }

  @Override
  public void setPerformanceRate(double performanceRate) {
    this.performanceRate = performanceRate;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Department getDepartment() {
    return department;
  }

  @Override
  public double getSalary() {
    return salary;
  }

  @Override
  public int getYearsOfExperience() {
    return yearsOfExperience;
  }

  @Override
  public double getPerformanceRate() {
    return performanceRate;
  }
}
