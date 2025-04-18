package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;
import com.employeemis.models.Department;
import com.employeemis.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Employees extends Protected {
  public Employees(Main app) {
    super(app);
    Helpers.Printer.important("Employees Menu");
  }

  private void previewTable(String title, List<Employee<Integer>> employees) {
    List<String> columns = new ArrayList<>(List.of("ID", "Name", "Department", "Salary", "Years of Experience", "Performance Rate"));
    Function<Employee<Integer>, List<String>> getRow = (e) -> new ArrayList<>(List.of(
      String.valueOf(e.getId()),
      e.getName(),
      e.getDepartment().getName(),
      String.valueOf(e.getSalary()),
      String.valueOf(e.getYearsOfExperience()),
      String.valueOf(e.getPerformanceRate())
    ));
    Helpers.Printer.tabular(title, columns, employees.stream().map(getRow).toList());
  }

  private String createDepartment() {
    while (true) {
      try {
        System.out.println("Enter department name:");
        String name = Helpers.Prompter.get(getApp().getScanner());
        if (name.equals("..."))
          return name;

        Department<Integer> department = new Department<>(name);
        getApp().getDepartmentRepository().add(department);
        return String.valueOf(department.getId());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void processList(int choice) {
    List<Employee<Integer>> employees = choice == 1 ?
      getApp().getEmployeeRepository().getAll() :
      getApp().getEmployeeRepository().getTop5Paid();
    previewTable(choice == 1 ? "List of All Employees" : "List of Top 5 Paid Employees", employees);
  }

  private void processCreate() {
    Helpers.Printer.important("CREATE EMPLOYEE RECORD");
    while (true) {
      try {
        // Get employee ID
        System.out.println("Enter employee ID (must be a number): ");
        String employeeId = Helpers.Prompter.getNumber(getApp().getScanner());
        // Enforce going back
        if (employeeId.equals("..."))
          return;

        // Get employee full name
        System.out.println("Enter employee name(s): ");
        String fullName = Helpers.Prompter.get(getApp().getScanner());
        // Enforce going back
        if (fullName.equals("..."))
          return;

        // Select department
        List<Department<Integer>> listOfDepartments = getApp().getDepartmentRepository().getAll();
        String departmentSelection;
        int selectedDepartmentID;
        // If none available, create one
        if (listOfDepartments.isEmpty()) {
          departmentSelection = createDepartment();
          if (departmentSelection.equals("..."))
            return;
          selectedDepartmentID = Integer.parseInt(departmentSelection);
        }
        // If available, select one
        else {
          departmentSelection = Helpers.select(
            "Select department: ", getApp().getScanner(),
            listOfDepartments.stream().map(Department::getName).toList());
          // Enforce going back
          if (departmentSelection.equals("..."))
            return;
          selectedDepartmentID = listOfDepartments.get(Integer.parseInt(departmentSelection) - 1).getId();
        }

        // Get employee salary
        System.out.println("Enter salary: ");
        String salary = Helpers.Prompter.getNumber(getApp().getScanner());
        // Enforce going back
        if (salary.equals("..."))
          return;

        // Get employee years of experience
        System.out.println("Enter years of experience: ");
        String yearsOfExperience = Helpers.Prompter.getNumber(getApp().getScanner());
        // Enforce going back
        if (yearsOfExperience.equals("..."))
          return;

        // Get employee ID
        System.out.println("Enter performance rate: ");
        String performance = Helpers.Prompter.getNumber(getApp().getScanner());
        // Enforce going back
        if (performance.equals("..."))
          return;

        // Now, create employee record
        Employee<Integer> employee = new Employee<>(
          Integer.parseInt(employeeId),
          fullName,
          getApp().getDepartmentRepository().get(selectedDepartmentID),
          Double.parseDouble(salary),
          Integer.parseInt(yearsOfExperience),
          Double.parseDouble(performance)
        );
        getApp().getEmployeeRepository().add(employee);
        Helpers.Printer.important("Employee created successfully!!");
        return;
      } catch (Exception e) {
        Helpers.Printer.important(e.getMessage());
      }
    }
  }

  private void processUpdate() {
    List<Employee<Integer>> employees = getApp().getEmployeeRepository().getAll();
    String selection = Helpers.select(
      "Select employee to update: ",
      getApp().getScanner(),
      employees.stream()
        .map(e -> String.format("%s (ID-%s)", e.getName(), e.getId()))
        .toList());
    if (selection.equals("..."))
      return;

    while (true) {
      try {
        List<String> fields = new ArrayList<>(
          List.of("name", "department", "salary", "yearsOfExperience", "performanceRate"));
        // Get field name to be updated
        String attribute = Helpers.select("Select field to be updated: ", getApp().getScanner(), fields);
        if (attribute.equals("..."))
          return;

        // Parse selected attribute to integer
        int selectedAttribute = Integer.parseInt(attribute) - 1;
        // Query selected employee
        Employee<Integer> selectedEmployee = employees.get(Integer.parseInt(selection) - 1);

        switch (fields.get(selectedAttribute)) {
          // This deals with department updates
          case "department" -> {
            List<Department<Integer>> listOfDepartments = getApp().getDepartmentRepository().getAll();
            String value = Helpers.select(
              "Select department: ", getApp().getScanner(),
              listOfDepartments.stream()
                .map(d ->
                  d.getId().equals(selectedEmployee.getDepartment().getId()) ?
                    d.getName() + " (*)" :
                    d.getName()
                ).toList());
            if (value.equals("..."))
              return;

            Department<Integer> selectedDepartment = listOfDepartments.get(Integer.parseInt(value) - 1);
            // New department is the same as the selected one, abort
            if (selectedDepartment.getId().equals(selectedEmployee.getDepartment().getId()))
              throw new IllegalArgumentException("You selected the same department");
            getApp().getEmployeeRepository().update(selectedEmployee.getId(), "department", selectedDepartment);
          }

          // This deals with name updates
          case "name" -> {
            System.out.println("Enter value of `name`:");
            String name = Helpers.Prompter.get(getApp().getScanner());
            if (name.equals("..."))
              return;
            getApp().getEmployeeRepository().update(selectedEmployee.getId(), "name", name);
          }

          // This block only deals with yearsOfExperience updates
          case "yearsOfExperience" -> {
            System.out.println("Enter value of `yearsOfExperience`:");
            String yearsOfExperience = Helpers.Prompter.getNumber(getApp().getScanner());
            if (yearsOfExperience.equals("..."))
              return;
            getApp().getEmployeeRepository()
              .update(selectedEmployee.getId(), "yearsOfExperience", Integer.parseInt(yearsOfExperience));
          }

          // This section deals with performanceRate & salary updates
          default -> {
            System.out.printf("Enter value of `%s`:%n", fields.get(selectedAttribute));
            String value = Helpers.Prompter.getNumber(getApp().getScanner());
            if (value.equals("..."))
              return;
            getApp().getEmployeeRepository()
              .update(selectedEmployee.getId(), fields.get(selectedAttribute), Double.parseDouble(value));
          }
        }
        Helpers.Printer.important(String.format("Employee no~(%s) was successfully updated!!", selection));
        return;
      } catch (Exception e) {
        Helpers.Printer.important(e.getMessage());
      }
    }
  }

  private void processRemoval() {
    List<Employee<Integer>> employees = getApp().getEmployeeRepository().getAll();
    String selection = Helpers.select(
      "Select employee to delete: ",
      getApp().getScanner(),
      employees.stream()
        .map(e -> String.format("%s (ID-%s)", e.getName(), e.getId()))
        .toList()
    );
    if (selection.equals("..."))
      return;

    getApp().getEmployeeRepository()
      .remove(employees.get(Integer.parseInt(selection) - 1).getId());
    Helpers.Printer.important(String.format("Employee no~(%s) was successfully deleted!!", selection));
  }

  private void processCRUD(int choice) {
    switch (choice) {
      case 4:
        processCreate();
        break;
      case 5:
        processUpdate();
        break;
      default:
        processRemoval();
    }
  }

  private void processReports(int choice) {}

  private void processFiltering(int choice) {}

  private void processSorting(int choice) {
  }

  private void dispatchSelection(int selection) {
    if (selection < 3)
      processList(selection);
    else if (selection < 4)
      processReports(selection);
    else if (selection < 7)
      processCRUD(selection);
    else if (selection < 11)
      processFiltering(selection);
    else
      processSorting(selection);
  }

  @Override
  public void process() throws IllegalAccessException {
    super.process();
    while (true) {
      String choice = Helpers.select("Select: ", getApp().getScanner(), new ArrayList<>(List.of(
        "List All Employees",
        "List Top 5 Paid Employees",
        "Show Employee Salary Average (By Department)",
        "Create Employee",
        "Update Employee",
        "Delete Employee",
        "Filter (By Department)",
        "Filter (By Name)",
        "Filter (By Salary Range ~ 500-670)",
        "Filter (By Performance >= x)",
        "Sort (By Salary - DESC)",
        "Sort (By Years of Experience - DESC)",
        "Sort (By Performance Rate - DESC)"
      )));

      if (choice.equals("...")) {
        getApp().backward("...");
        break;
      }
      dispatchSelection(Integer.parseInt(choice));
    }
  }
}
