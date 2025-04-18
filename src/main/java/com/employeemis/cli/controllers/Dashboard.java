package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Protected {
  public Dashboard(Main app) {
    super(app);
  }

  @Override
  public void process() throws IllegalAccessException {
    super.process();
    Helpers.Printer.important("DASHBOARD MENU");
    String route = Helpers.select("Select: ", getApp().getScanner(), new ArrayList<>(List.of(
      "Users",
      "Employees",
      "Departments",
      "Permissions"
    )));
    switch (route) {
      case "1":
        getApp().replace("users");
        break;
      case "2":
        getApp().replace("employees");
        break;
      case "3":
        getApp().replace("departments");
        break;
      case "4":
        getApp().replace("permissions");
      default:
        getApp().backward("...");
    }
  }
}
