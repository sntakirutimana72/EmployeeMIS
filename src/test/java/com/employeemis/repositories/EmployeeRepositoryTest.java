package com.employeemis.repositories;

import com.employeemis.models.Department;
import com.employeemis.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class EmployeeRepositoryTest {
  private EmployeeRepository<Integer> repository;

  private Employee<Integer> create(int id) {
    Department<Integer> department = new Department<>("HR");
    return new Employee<>(id, "john", department, 23, 2, 3);
  }

  @BeforeEach
  void setUp() {
    repository = new EmployeeRepository<>();
    repository.add(create(1));
  }

  @AfterEach
  void tearDown() {
    repository = null;
  }

  @Test
  void expectGetToThrowErrorWhenNoEmployeeFound() {
    assertThrows(NoSuchElementException.class, () -> repository.get(2));
  }

  @Test
  void expectGetToReturnEmployee() {
    assertInstanceOf(Employee.class, repository.get(1));
  }

  @Test
  void expectAddToStoreEmployeeById() {
    Employee<Integer> employee = create(2);
    repository.add(employee);
    assertEquals(employee, repository.get(employee.getId()));
  }

  @Test
  void expectDuplicateKeyToThrow() {
    assertThrows(IllegalArgumentException.class, () -> repository.add(create(1)));
  }

  @Test
  void expectRemoveToDeleteExistingEmployee() {
    repository.remove(1);
    assertThrows(NoSuchElementException.class, () -> repository.get(1));
  }

  @Test
  void expectUpdateToCallSetterOfGivenAttribute() throws Exception {
    repository.update(1, "name", "New Dynamic Value");
    assertEquals("New Dynamic Value", repository.get(1).getName());
  }

  @Test
  void expectUpdateToThrowWhenNoSetterFound() {
    assertThrows(Exception.class, () -> repository.update(1, "age", "123"));
  }

  @Test
  void expectUpdateToThrowDueToValidationError() {
    assertThrows(Exception.class, () -> repository.update(1, "name", "123"));
  }

  @Test
  void getAll() {
    repository.add(create(2));
    assertEquals(2, repository.getAll().size());
  }
}