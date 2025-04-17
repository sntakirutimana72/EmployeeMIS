package com.employeemis.models;

import java.time.LocalDate;

public interface Trackable {
  // Get Unique ID
  int getId();

  // Creation date
  LocalDate getCreatedAt();

  // Last update date
  LocalDate getUpdatedAt();
}
