package com.employeemis.utils;

import java.util.List;

public class Filters {
  public static class Employee {
    public static <K> List<com.employeemis.models.Employee<K>> byDepartment(List<com.employeemis.models.Employee<K>> employees, String tag) {
      return employees.stream()
        .filter(e -> e.getDepartment().getName().equalsIgnoreCase(tag))
        .toList();
    }

    public static <K> List<com.employeemis.models.Employee<K>> byName(List<com.employeemis.models.Employee<K>> employees, String pattern) {
      return employees.stream()
        .filter(e -> e.getName().toLowerCase().contains(pattern.toLowerCase()))
        .toList();
    }
  }
}
