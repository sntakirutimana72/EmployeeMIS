package com.employeemis.models;

public interface Employable {
  // Get & set employee name
  String getName();
  void setName(String name);

  // Get & set department under which employee is allocated
  Department getDepartment();
  void setDepartment(Department department);

  // Get & set employee salary
  double getSalary();
  void setSalary(double salary);

  // Get & set employee years of experience
  int getYearsOfExperience();
  void setYearsOfExperience(int yearsOfExperience);

  // Get & set employee performance rate
  double getPerformanceRate();
  void setPerformanceRate(double performanceRate);
}
