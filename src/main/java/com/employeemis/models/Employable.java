package com.employeemis.models;

public interface Employable<T> extends Comparable<Employable<T>> {
  // Get & set employee name
  String getName();
  void setName(String name);

  // Get & set department under which employee is allocated
  Department<T> getDepartment();
  void setDepartment(Department<T> department);

  // Get & set employee salary
  double getSalary();
  void setSalary(double salary);

  // Get & set employee years of experience
  int getYearsOfExperience();
  void setYearsOfExperience(int yearsOfExperience);

  // Get & set employee performance rate
  double getPerformanceRate();
  void setPerformanceRate(double performanceRate);

  // Give salary raise based on performance rate
  double giveSalaryRaise();
}
