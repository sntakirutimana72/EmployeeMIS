package com.employeemis.utils;

import com.employeemis.models.Employee;

import java.util.Comparator;

public class Comparators {
  public static class Employee {
    public static <T> Comparator<com.employeemis.models.Employee<T>> byExperienceDesc() {
      return Comparator.comparingInt(com.employeemis.models.Employee<T>::getYearsOfExperience).reversed();
    }

    public static <T> Comparator<com.employeemis.models.Employee<T>> byPerformanceDesc() {
      return Comparator.comparingDouble(com.employeemis.models.Employee<T>::getPerformanceRate).reversed();
    }

    public static <T> Comparator<com.employeemis.models.Employee<T>> bySalaryDesc() {
      return Comparator.comparingDouble(com.employeemis.models.Employee<T>::getSalary).reversed();
    }
  }
}
