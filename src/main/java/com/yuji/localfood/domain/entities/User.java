package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import com.yuji.localfood.domain.validations.Preconditions;
import com.yuji.localfood.domain.valueobjects.Email;

public final class User {
  private final UUID id;
  private final String name;
  private final Email email;
  private final String locale;
  private final Instant createdAt;
  private final Instant updatedAt;

  public User(UUID id, String name, Email email,
      String locale, Instant createdAt, Instant updatedAt) {
    this.id = Preconditions.requireNonNullArg(id, "id");
    this.name = Preconditions.requireNonNullArg(name, "name").trim();
    this.email = Preconditions.requireNonNullArg(email, "email");
    this.locale = (locale == null || locale.isBlank()) ? "pt-BR" : locale;
    this.createdAt = Preconditions.requireNonNullArg(createdAt, "createdAt");
    this.updatedAt = Preconditions.requireNonNullArg(updatedAt, "updatedAt");

  }

  public static User createNew(String name, Email email, String locale) {
    var now = Instant.now();
    return new User(UUID.randomUUID(), name, email, locale, now, now);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }

  public String getLocale() {
    return locale;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public User withUpdatedAt(Instant newUpdatedAt) {
    return new User(id, name, email, locale, createdAt, newUpdatedAt);
  }

  public User rename(String newName) {
    return new User(id, newName, email, locale, createdAt, Instant.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof User other))
      return false;
    return id.equals(other.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "User{id=%s, name=%s, email=%s, locale=%s, createdAt=%s, updatedAt=%s}"
        .formatted(id, name, email, locale, createdAt, updatedAt);
  }
}
