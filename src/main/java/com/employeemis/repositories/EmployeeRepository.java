package com.employeemis.repositories;

import com.employeemis.models.Employee;
import com.employeemis.utils.Comparators;

import java.util.List;

public class EmployeeRepository<K> extends RepositoryAbstract<K, Employee<K>> {
  public List<Employee<K>> getTop5Paid() {
    return getAll().stream()
      .sorted(Comparators.Employee.bySalaryDesc())
      .limit(5)
      .toList();
  }

  public double getSalaryAverageByDepartment(String departmentName) {
    return getAll().stream()
      .filter(e -> e.getDepartment().getName().equalsIgnoreCase(departmentName))
      .mapToDouble(Employee::getSalary)
      .average()
      .orElse(0.0); // default if no employee found
  }
}
