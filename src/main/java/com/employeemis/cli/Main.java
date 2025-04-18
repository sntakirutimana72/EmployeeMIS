package com.employeemis.cli;

import com.employeemis.repositories.DepartmentRepository;
import com.employeemis.repositories.EmployeeRepository;
import com.employeemis.repositories.PermissionRepository;
import com.employeemis.repositories.UserRepository;

import java.util.Scanner;

public class Main extends Navigator {
  private boolean loggedIn;
  private final Scanner scanner;
  private final UserRepository userRepository;
  private final EmployeeRepository<Integer> employeeRepository;
  private final DepartmentRepository<Integer> departmentRepository;
  private final PermissionRepository permissionRepository;

  private Main() {
    super();

    userRepository = new UserRepository();
    employeeRepository = new EmployeeRepository<>();
    departmentRepository = new DepartmentRepository<>();
    permissionRepository = new PermissionRepository();
    scanner = new Scanner(System.in);

    // On startup, system must push default route by default
    forward("");
    Helpers.Printer.important("Welcome To Employee Management System");
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public PermissionRepository getPermissionRepository() {
    return permissionRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public EmployeeRepository<Integer> getEmployeeRepository() {
    return employeeRepository;
  }

  public DepartmentRepository<Integer> getDepartmentRepository() {
    return departmentRepository;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void run() {
    //noinspection InfiniteLoopStatement
    while (true) {
      try {
        switch (current()) {
          case "/login":
            new com.employeemis.cli.controllers.Login(this).process();
            break;
          case "/dashboard":
            new com.employeemis.cli.controllers.Dashboard(this).process();
            break;
          case "/employees":
            new com.employeemis.cli.controllers.Employees(this).process();
            break;
          case "/users":
            new com.employeemis.cli.controllers.Users(this).process();
            break;
          case "/loggedIn":
            loggedIn = true;
            replace("dashboard");
            break;
          default:
            new com.employeemis.cli.controllers.Index(this).process();
        }
      } catch (Exception e) {
        Helpers.Printer.important(e.getMessage());
        replace(isLoggedIn() ? "dashboard" : "");
      }
    }
  }

  public static void main(String[] args) {
    new Main().run();
  }
}
