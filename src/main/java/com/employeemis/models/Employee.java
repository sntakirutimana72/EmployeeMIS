package com.employeemis.models;

public class Employee<T> extends ActivableEntity<T> implements Employable<T> {
  // Contracted from Employable interface
  private String name;
  private Department<T> department;
  private double salary;
  private int yearsOfExperience;
  private double performanceRate;

  public Employee(T id, String name, Department<T> department, double salary, int yearsOfExperience, double performanceRate) {
    super(id);
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

  private void assignDepartment(Department<T> department) {
    this.department = department;
    department.addEmployee(this);
  }

  @Override
  public void setDepartment(Department<T> department) {
    if (this.department == department)
      return;
    Department<T> previous = this.department;
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
  public Department<T> getDepartment() {
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

  @Override
  public int compareTo(Employable<T> emp) {
    return Integer.compare(emp.getYearsOfExperience(), this.getYearsOfExperience());
  }
}
