package com.yuji.localfood.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public final class PhoneNumber {
  private static final Pattern E164 = Pattern.compile("^\\+[1-9]\\d{1,14}$");

  private final String value;

  private PhoneNumber(String value) {
    this.value = value;
  }

  public static PhoneNumber of(String raw) {
    if (raw == null)
      throw new IllegalArgumentException("Phone number is required");

    var cleaned = raw.trim().replaceAll("[\\s()-]", ""); 

    if (!E164.matcher(cleaned).matches()) {
      throw new IllegalArgumentException("Invalid phone number. Use E.164 format");
    }

    return new PhoneNumber(cleaned);
  }

  public String value() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public String formatted() {
    if (value.startsWith("+55") && value.length() == 14) {
      return String.format("(%s) %s-%s",
          value.substring(3, 5),
          value.substring(5, 9),
          value.substring(9));
    }
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof PhoneNumber other))
      return false;
    return Objects.equals(value, other.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
