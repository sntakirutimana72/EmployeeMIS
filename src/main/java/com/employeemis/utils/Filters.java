package com.employeemis.utils;

import java.util.List;

public class Filters {
  public static class Employee {
    public static <K> List<com.employeemis.models.Employee<K>> getAllByDepartment(List<com.employeemis.models.Employee<K>> employees, String department) {
      return employees.stream()
        .filter(e -> e.getDepartment().getName().equalsIgnoreCase(department))
        .toList();
    }
  }
}
