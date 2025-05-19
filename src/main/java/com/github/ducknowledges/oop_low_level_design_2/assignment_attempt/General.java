package com.github.ducknowledges.oop_low_level_design_2.assignment_attempt;

import java.io.Serializable;

class General implements Serializable {

  @SuppressWarnings("unchecked")
  public static <T extends General> T assignmentAttempt(Object source, Class<T> targetClass) {
    if (!targetClass.isInstance(source)) {
      return (T) new None();
    }
    return (T) source;
  }
}

class Any extends General {}

final class None extends Any {}
