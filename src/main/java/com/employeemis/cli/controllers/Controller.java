package com.employeemis.cli.controllers;

public abstract class Controller {
  private final com.employeemis.cli.Main app;

  public Controller(com.employeemis.cli.Main app) {
    this.app = app;
  }

  public com.employeemis.cli.Main getApp() {
    return app;
  }
}
