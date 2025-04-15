package com.employeemis.models;

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
    setDepartment(department);
    setSalary(salary);
    setYearsOfExperience(yearsOfExperience);
    setPerformanceRate(performanceRate);
    setIsActive(true);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setDepartment(Department department) {
    if (this.department == department)
      return;
    Department previousDepartment = this.department;
    this.department = department;

    previousDepartment.removeEmployee(this);
    department.addEmployee(this);
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
  public String getDepartment() {
    return department.getName();
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
