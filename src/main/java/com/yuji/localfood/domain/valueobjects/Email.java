package com.yuji.localfood.domain.valueobjects;

import java.net.IDN;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {
  private static final int MAX_TOTAL_LEN = 320; 
  private static final int MAX_LOCAL_LEN = 64;

  private static final Pattern SIMPLE = Pattern.compile(
      "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
      Pattern.CASE_INSENSITIVE);

  private final String value; 

  private Email(String normalized) {
    this.value = normalized;
  }

  public static Email of(String raw) {
    if (raw == null)
      throw new IllegalArgumentException("Email é obrigatório");

    var trimmed = raw.trim();

    var at = trimmed.lastIndexOf('@');
    if (at <= 0 || at == trimmed.length() - 1) {
      throw new IllegalArgumentException("Email inválido");
    }

    var local = trimmed.substring(0, at);
    var domainRaw = trimmed.substring(at + 1);

    if (local.length() > MAX_LOCAL_LEN)
      throw new IllegalArgumentException("Local-part muito longo");
    if (trimmed.length() > MAX_TOTAL_LEN)
      throw new IllegalArgumentException("Email muito longo");

    var domain = IDN.toASCII(domainRaw, IDN.ALLOW_UNASSIGNED);

    var normalized = (local + "@" + domain).toLowerCase();

    if (!SIMPLE.matcher(normalized).matches()) {
      throw new IllegalArgumentException("Formato de email inválido");
    }

    for (var label : domain.split("\\.")) {
      if (label.isEmpty() || label.length() > 63)
        throw new IllegalArgumentException("Domínio inválido");
      if (label.startsWith("-") || label.endsWith("-"))
        throw new IllegalArgumentException("Domínio inválido");
    }

    return new Email(normalized);
  }

  public String value() {
    return value;
  }

  public String toString() {
    return value;
  }

  public String localPart() {
    return value.substring(0, value.indexOf('@'));
  }

  public String domain() {
    return value.substring(value.indexOf('@') + 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Email other))
      return false;
    return Objects.equals(value, other.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
