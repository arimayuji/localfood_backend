// src/test/java/com/yuji/localfood/builders/UserTestDataBuilder.java
package com.yuji.localfood.builders;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import com.yuji.localfood.domain.entities.User;
import com.yuji.localfood.domain.valueobjects.Email;

public class UserTestDataBuilder {
  private static final Random rnd = new Random(42);

  private UUID id = UUID.randomUUID();
  private String name = "User-" + rnd.nextInt(10_000);
  private Email email = Email.of("user" + rnd.nextInt(10_000) + "@mail.com");
  private final String locale = "pt-BR";
  private final Instant createdAt = Instant.now();
  private final Instant updatedAt = Instant.now();

  public static UserTestDataBuilder aUser() {
    return new UserTestDataBuilder();
  }

  public UserTestDataBuilder withId(UUID id) {
    this.id = id;
    return this;
  }

  public UserTestDataBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public UserTestDataBuilder withEmail(String email) {
    this.email = Email.of(email);
    return this;
  }

  public User build() {
    return new User(id, name, email, locale, createdAt, updatedAt);
  }
}
