package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

import java.util.ArrayList;
import java.util.List;

public class Permissions extends Controller {
  public Permissions(Main app) {
    super(app);
  }

  public void process() throws Helpers.Errors.AbortException {
    Helpers.Printer.alert("Permission Menu");

    int choice = Helpers.Selectors.select("*** Select options ***", getApp().getScanner(), new ArrayList<>(List.of(
      "List All Permissions",
      "Create Permission",
      "Update Permission",
      "Delete Permission"
    )));
  }
}
