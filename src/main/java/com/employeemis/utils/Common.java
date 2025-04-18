package com.employeemis.utils;

import java.lang.reflect.Method;

public class Common {
  private static Class<?> getPrimitiveType(Class<?> wrapper) {
    if (wrapper == Integer.class) return int.class;
    if (wrapper == Boolean.class) return boolean.class;
    if (wrapper == Double.class) return double.class;
    if (wrapper == Float.class) return float.class;
    if (wrapper == Long.class) return long.class;
    if (wrapper == Short.class) return short.class;
    if (wrapper == Byte.class) return byte.class;
    if (wrapper == Character.class) return char.class;
    return wrapper;
  }

  public static <T> Method getClassMethod(Class<?> clazz, String setterName, T value) throws NoSuchMethodException {
    for (Method setter : clazz.getMethods()) {
      if (setter.getName().equals(setterName) && setter.getParameterCount() == 1) {
        Class<?> paramType = setter.getParameterTypes()[0];
        if (paramType.isPrimitive()) {
          // Unbox the wrapper type to compare with primitive
          if (getPrimitiveType(value.getClass()) == paramType) {
            return setter;
          }
        } else if (paramType.isAssignableFrom(value.getClass())) {
          return setter;
        }
      }
    }
    throw new NoSuchMethodException(String.format("No setter found for `%s`", setterName.substring(3)));
  }
}
