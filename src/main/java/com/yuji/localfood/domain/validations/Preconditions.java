package com.yuji.localfood.domain.validations;

public final class Preconditions {
  private Preconditions() {
  }

  public static <T> T requireNonNullArg(T value, String name) {
    if (value == null)
      throw new IllegalArgumentException(name + " must not be null");
    return value;
  }

  public static String requireNotBlank(String value, String name) {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException(name + " must not be blank");
    return value;
  }

  public static int requireRange(int value, int min, int max) {
    if (value < min || value > max)
      throw new IllegalArgumentException("value must be between " + min + " and " + max);
    return value;
  }

  public static void requireState(boolean condition, String message) {
    if (!condition)
      throw new IllegalStateException(message);
  }
}