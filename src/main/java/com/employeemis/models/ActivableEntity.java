package com.employeemis.models;

public abstract class ActivableEntity extends Entity implements Activable {
  private boolean isActive;

  @Override
  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean getIsActive() {
    return isActive;
  }
}
