package com.employeemis.models;

import java.time.LocalDate;

public interface Trackable {
  // Get Unique ID
  int getID();

  // Creation date
  LocalDate getCreatedAt();

  // Last update date
  LocalDate getUpdatedAt();
}
