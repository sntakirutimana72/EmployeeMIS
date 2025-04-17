package com.employeemis.repositories;

import com.employeemis.models.Trackable;

import java.lang.reflect.Method;
import java.util.*;

public abstract class RepositoryAbstract<K, V extends Trackable<K>> implements Repository<K, V> {
  private final Map<K, V> repository;

  public RepositoryAbstract() {
    repository = new HashMap<>();
  }

  @Override
  public V get(K key) throws NoSuchElementException {
    V entity = repository.get(key);
    if (Objects.isNull(entity))
      throw new NoSuchElementException("Resource not found");
    return entity;
  }

  @Override
  public void add(V entity) {
    K uid = entity.getId();
    if (repository.containsKey(uid))
      throw new IllegalArgumentException(
        String.format("%s with id=`%s` already exists", entity.getClass().getName(), uid));
    repository.put(entity.getId(), entity);
  }

  @Override
  public void remove(K key) {
    repository.remove(key);
  }

  @Override
  public <T> void update(K key, String attribute, T value) throws Exception {
    V entity = this.get(key);
    String setterName = "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);

    try {
      Method setter = findSetter(entity.getClass(), setterName, value);
      setter.invoke(entity, value);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  private <T> Method findSetter(Class<?> clazz, String setterName, T value) throws NoSuchMethodException {
    for (Method setter : clazz.getMethods()) {
      if (setter.getName().equals(setterName)
        && setter.getParameterCount() == 1
        && setter.getParameterTypes()[0].isAssignableFrom(value.getClass())) {
        return setter;
      }
    }
    throw new NoSuchMethodException(String.format("No setter found for `%s`", setterName.substring(3)));
  }

  @Override
  public List<V> getAll() {
    return repository.values().stream().toList();
  }
}
