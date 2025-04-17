package com.employeemis.models;

import java.time.LocalDate;

public abstract class Entity implements Trackable {
  private static int uniqueIDTracker = 0;

  private final int id;
  private final LocalDate createdAt;
  private LocalDate updatedAt;

  public Entity() {
    id = ++uniqueIDTracker;
    createdAt = LocalDate.now();
    setUpdatedAt();
  }

  protected void setUpdatedAt() {
    updatedAt = LocalDate.now();
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public LocalDate getCreatedAt() {
    return createdAt;
  }

  @Override
  public LocalDate getUpdatedAt() {
    return updatedAt;
  }
}
