package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.yuji.localfood.domain.valueobjects.Email;

public class UserTest {

  private final UUID id = UUID.randomUUID();
  private final Instant now = Instant.parse("2023-01-01T00:00:00Z");
  private final Email email = Email.of("email@domain.com");

  @Test
  void shouldCreateValidUser() {
    var user = new User(id, "name", email, "locale", now, now);

    assertEquals(id, user.getId());
    assertEquals("name", user.getName());
    assertEquals(email, user.getEmail());
    assertEquals("locale", user.getLocale());
    assertEquals(now, user.getCreatedAt());
    assertEquals(now, user.getUpdatedAt());
  }

  @Test
  void shouldRejectNullFields() {
    Exception nameNullException = assertThrows(IllegalArgumentException.class,
        () -> new User(id, null, email, "locale", now, now));
    Exception emailNullException = assertThrows(IllegalArgumentException.class,
        () -> new User(id, "name", null, "locale", now, now));
    Exception localeNullException = assertThrows(IllegalArgumentException.class,
        () -> new User(id, "name", email, null, now, now));

    assertEquals("name", nameNullException.getMessage());
    assertEquals("email", emailNullException.getMessage());
    assertEquals("locale", localeNullException.getMessage());
  }
}
