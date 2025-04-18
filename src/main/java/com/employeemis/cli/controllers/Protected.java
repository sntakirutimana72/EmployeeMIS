package com.employeemis.cli.controllers;

public abstract class Protected extends Controller {
  public Protected(com.employeemis.cli.Main app) {
    super(app);
  }

  @Override
  public final boolean isProtected() {
    return true;
  }

  @Override
  public boolean isAllowed() {
    return getApp().isLoggedIn();
  }
}
