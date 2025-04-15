package com.employeemis.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class User extends ActivableEntity {
  private String username;
  private String password;
  private LocalDate lastLogin = null;
  private final int employeeID;
  private final Set<Permission> permissions;

  public User(String username, String password, int employeeID) {
    super();
    setUsername(username);
    setPassword(password);
    setIsActive(true);
    setPermissions(new Permission("Tenant", "all"));

    this.permissions = new HashSet<>();
    this.employeeID = employeeID;
  }

  public void setPermissions(Permission ... permissions) {
    this.permissions.addAll(Arrays.stream(permissions).toList());
  }

  public void setLastLogin() {
    lastLogin = LocalDate.now();
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
    setUpdatedAt();
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public LocalDate getLastLogin() {
    return lastLogin;
  }
}
