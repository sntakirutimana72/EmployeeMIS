package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

import java.util.ArrayList;
import java.util.List;

public class Departments extends Controller {
  public Departments(Main app) {
    super(app);
  }

  public void process() throws Helpers.Errors.AbortException {
    Helpers.Printer.alert("Department Menu");

    int choice = Helpers.Selectors.select("*** Select options ***", getApp().getScanner(), new ArrayList<>(List.of(
      "List All Departments",
      "Create Department",
      "Update Department",
      "Delete Department"
    )));
  }
}
