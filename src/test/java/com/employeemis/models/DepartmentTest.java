package com.employeemis.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
  private Department<String> department;

  @BeforeEach
  void setUp() {
    department = new Department<>("HR");
  }

  @AfterEach
  void tearDown() {
    department = null;
  }

  @Test
  void testGetNameReturnsNameAttributeValue() {
    assertEquals("HR", department.getName());
  }

  @Test
  void testSetNameChangesNameAttributeValue() {
    department.setName("IT");
    assertEquals("IT", department.getName());
  }
}