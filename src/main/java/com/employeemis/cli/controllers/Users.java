package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

import java.util.ArrayList;
import java.util.List;

public class Users extends Protected {
  public Users(Main app) {
    super(app);
  }

  @Override
  public void process() throws IllegalAccessException {
    super.process();
    Helpers.Printer.important("USER MENU");
    String route = Helpers.select("Select: ", getApp().getScanner(), new ArrayList<>(List.of(
      "List All Users",
      "Create User",
      "Update User",
      "Change password"
    )));
  }
}
