package com.employeemis.cli.controllers;

public abstract class Public extends Controller {
  public Public(com.employeemis.cli.Main app) {
    super(app);
  }

  @Override
  public final boolean isProtected() {
    return false;
  }

  @Override
  public final boolean isAllowed() {
    return true;
  }
}
