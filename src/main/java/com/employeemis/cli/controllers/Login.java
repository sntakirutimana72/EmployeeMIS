package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

public class Login extends Controller {

  public Login(Main app) {
    super(app);
    Helpers.Printer.alert("Login Form");
  }

  public int post() throws Helpers.Errors.AbortException {
    while (true) {
      String username = Helpers.Prompt.getText(getApp().getScanner(), "Enter username:\n> ");
      String password = Helpers.Prompt.getText(getApp().getScanner(), "Enter password:\n> ");

      if (username.equalsIgnoreCase("testUser") && password.equals("pass@123"))
        return 1;
      Helpers.Printer.alert("Authentication Failed");
    }
  }
}
