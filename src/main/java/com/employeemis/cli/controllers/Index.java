package com.employeemis.cli.controllers;

import com.employeemis.cli.Helpers;

import java.util.ArrayList;
import java.util.List;

public class Index extends Public {

  public Index(com.employeemis.cli.Main app) {
    super(app);
  }

  @Override
  public void process() throws IllegalAccessException {
    super.process();
    // Display available menu on this level, more like a landing page
    String response = Helpers.select("Menu", getApp().getScanner(), new ArrayList<>(List.of("Login")));
    // Update navigation history based on choice
    if (response.equals("1"))
      getApp().replace("login");
  }
}
