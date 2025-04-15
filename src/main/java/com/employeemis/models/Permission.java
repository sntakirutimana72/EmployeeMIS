package com.employeemis.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Permission extends Entity {
  private String name;
  private final String resource;
  private final Set<PermissionActions> actions;

  public Permission(String name, String resource) {
    this(name, resource, PermissionActions.READ);
  }

  public Permission(String name, String resource, PermissionActions ... actions) {
    this.resource = resource;
    this.actions = new HashSet<>();

    setName(name);
    setActions(actions);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setActions(PermissionActions ... actions) {
    this.actions.addAll(Arrays.stream(actions).toList());
  }

  public String getName() {
    return name;
  }

  public String getResource() {
    return resource;
  }

  public String[] getActions() {
    return actions.stream().map(PermissionActions::getTag).toArray(String[]::new);
  }
}
