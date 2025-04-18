package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;
import com.employeemis.cli.Main;

public class Login extends Public {
  public Login(Main app) {
    super(app);
  }

  @Override
  public void process() throws IllegalAccessException {
    super.process();
    Helpers.Printer.important("Login Form");
    while (true) {
      System.out.println("Enter username:");
      String username = Helpers.Prompter.get(getApp().getScanner());
      System.out.println("Enter password:");
      String password = Helpers.Prompter.get(getApp().getScanner());

      if (username.equalsIgnoreCase("testUser") && password.equals("pass@123")) {
        getApp().replace("loggedIn");
        return;
      }
      Helpers.Printer.important("Authentication Failed");
    }
  }
}
