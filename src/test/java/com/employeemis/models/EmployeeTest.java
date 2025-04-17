package com.employeemis.models;

import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
  private Employee employee;

  @BeforeEach
  void setUp() {
    Department department = new Department("IT");
    employee = new Employee("Test", department, 20.3, 0, 5);
  }

  @AfterEach
  void tearDown() {
    employee = null;
  }

  @Test
  @DisplayName("Should be true")
  void testIsActiveAttributeIsSetToTrueByDefault() {
    assertTrue(employee.getIsActive());
  }

  @Test
  @DisplayName("Should have id set by default")
  void testIdAttributeIsSetByDefault() {
    assertTrue(employee.getId() > 0);
  }

  @Test
  @DisplayName("Should have getIsActive() method with boolean return type")
  void testHasGetIsActive() throws NoSuchMethodException {
    // Act
    Method method = Employee.class.getMethod("getIsActive");
    // Assert existence
    assertNotNull(method, "Method ::getIsActive() should exist");
    // Assert return type
    assertEquals(boolean.class, method.getReturnType(), "getIsActive() should return boolean");
  }

  @Test
  @DisplayName("Should have getUpdatedAt() method with LocalDate return type")
  void testHasGetUpdatedAt() throws NoSuchMethodException {
    // Act
    Method method = Employee.class.getMethod("getUpdatedAt");
    // Assert existence
    assertNotNull(method, "Method ::getUpdatedAt() should exist");
    // Assert return type
    assertEquals(LocalDate.class, method.getReturnType(), "getUpdatedAt() should return LocalDate");
  }

  @Test
  @DisplayName("Should have getCreatedAt() method with LocalDate return type")
  void testHasGetCreatedAt() throws NoSuchMethodException {
    // Act
    Method method = Employee.class.getMethod("getCreatedAt");
    // Assert existence
    assertNotNull(method, "Method ::getCreatedAt() should exist");
    // Assert return type
    assertEquals(LocalDate.class, method.getReturnType(), "getCreatedAt() should return LocalDate");
  }

  @Test
  @DisplayName("Should be able set department from department side")
  void testBelongsToFromDepartmentSide() {
    Department previous = employee.getDepartment();
    assertTrue(previous.getEmployees().contains(employee));
    Department current = new Department("HR");
    current.addEmployee(employee);
    assertEquals(employee.getDepartment().getId(), current.getId());
    assertFalse(previous.getEmployees().contains(employee));
  }

  @Test
  @DisplayName("Should be to update department from employee side")
  void testBelongsToFromSelf() {
    Department previous = employee.getDepartment();
    assertTrue(previous.getEmployees().contains(employee));
    Department current = new Department("HR");
    employee.setDepartment(current);
    assertEquals(current.getId(), employee.getDepartment().getId());
    assertFalse(previous.getEmployees().contains(employee));
    assertTrue(current.getEmployees().contains(employee));
  }
}