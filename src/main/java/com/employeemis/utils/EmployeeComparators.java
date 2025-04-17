package com.employeemis.utils;

import com.employeemis.models.Employee;

import java.util.Comparator;

public class EmployeeComparators {
  public static <T> Comparator<Employee<T>> byExperienceDesc() {
    return Comparator.comparingInt(Employee<T>::getYearsOfExperience).reversed();
  }

  public static <T> Comparator<Employee<T>> byPerformanceDesc() {
    return Comparator.comparingDouble(Employee<T>::getPerformanceRate).reversed();
  }

  public static <T> Comparator<Employee<T>> bySalaryDesc() {
    return Comparator.comparingDouble(Employee<T>::getSalary).reversed();
  }
}
