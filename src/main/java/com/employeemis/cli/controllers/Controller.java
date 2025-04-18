package com.employeemis.cli.controllers;

public abstract class Controller {
  private final com.employeemis.cli.Main app;

  public Controller(com.employeemis.cli.Main app) {
    this.app = app;
  }

  public com.employeemis.cli.Main getApp() {
    return app;
  }

  // If a controller requires special access, it must specify that
  abstract boolean isProtected();
  // If controller is protected, then define the scope of what's permissions
  abstract boolean isAllowed() throws IllegalAccessException;

  public void process() throws IllegalAccessException {
    if (!isProtected() && getApp().isLoggedIn())
      throw new IllegalAccessException("Redirecting to dashboard");
    if (isProtected() && !isAllowed())
      throw new IllegalAccessException("Unauthorized");
  }
}
