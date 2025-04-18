package com.employeemis.repositories;

import com.employeemis.models.Employee;
import com.employeemis.utils.Comparators;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeRepository<K> extends RepositoryAbstract<K, Employee<K>> {
  public List<Employee<K>> getTop5Paid() {
    return getAll().stream()
      .sorted(Comparators.Employee.bySalaryDesc())
      .limit(5)
      .collect(Collectors.toList());
  }

  public double getSalaryAverageByDepartment(String department) {
    return getAll().stream()
      .filter(e -> e.getDepartment().getName().equalsIgnoreCase(department))
      .mapToDouble(Employee::getSalary)
      .average()
      .orElse(0.0); // default if no employee found
  }

}
