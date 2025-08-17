package com.yuji.localfood.contracts;

import java.time.Instant;
import java.util.UUID;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yuji.localfood.domain.entities.User;
import com.yuji.localfood.domain.pagination.Page;
import com.yuji.localfood.domain.pagination.PageRequest;
import com.yuji.localfood.domain.repositories.UserRepository;
import com.yuji.localfood.domain.valueobjects.Email;

public abstract class UserRepositoryContractTest {

  protected abstract UserRepository repo();

  protected User newUser(String email, String name) {
    return new User(UUID.randomUUID(), name, Email.of(email), "pt-BR", Instant.now(), Instant.now());
  }

  @Test
  void createThenFindById() {
    var user = newUser("email", "name");
    repo().create(user);

    var found = repo().findById(user.getId());

    assertTrue(found.isPresent());
    assertEquals(user, found);
  }

  @Test
  void createWithDuplicateEmailMustFail() {
    var user1 = newUser("email", "name");
    var user2 = newUser("email", "name");

    repo().create(user1);
    Exception duplicateEmailException = assertThrows(IllegalArgumentException.class, () -> repo().create(user2));

    assertEquals("email", duplicateEmailException.getMessage());
  }

  @Test
  void updateRequiresExistingUser() {
    var user = newUser("email", "name");
    Exception userNotFoundException = assertThrows(IllegalArgumentException.class, () -> repo().update(user));

    assertEquals("user", userNotFoundException.getMessage());

    repo().create(user);

    var updated = new User(user.getId(), "new name", user.getEmail(), "pt-BR", Instant.now(), Instant.now());
    var saved = repo().update(updated);

    assertEquals("new name", saved.getName());
    assertEquals("new name", repo().findById(user.getId()).get().getName());
  }

  @Test
  void findByEmailOptional() {
    var email = Email.of("c@c.com");
    assertTrue(repo().findByEmail(email).isEmpty());

    var user = new User(UUID.randomUUID(), "name", email, "pt-BR", Instant.now(), Instant.now());
    repo().create(user);

    assertTrue(repo().findByEmail(email).isPresent());
  }

  @Test
  void existsByEmail() {
    var email = Email.of("c@c.com");
    assertTrue(!repo().existsByEmail(email));

    var user = new User(UUID.randomUUID(), "name", email, "pt-BR", Instant.now(), Instant.now());
    repo().create(user);
    assertTrue(repo().existsByEmail(email));
  }

  @Test
  void deleteById() {
    var user = new User(UUID.randomUUID(), "name", Email.of("c@c.com"), "pt-BR", Instant.now(), Instant.now());

    repo().create(user);
    repo().delete(user.getId());
    assertTrue(repo().findById(user.getId()).isEmpty());
  }

  @Test
  void findAll() {
    var user1 = new User(UUID.randomUUID(), "name", Email.of("c@c.com"), "pt-BR", Instant.now(), Instant.now());
    var user2 = new User(UUID.randomUUID(), "name", Email.of("c@c.com"), "pt-BR", Instant.now(), Instant.now());

    repo().create(user1);
    repo().create(user2);

    PageRequest pageRequest = PageRequest.of(0, 5);

    Page<User> page0 = repo().findAll(pageRequest);
    assertEquals(2, page0.content().size());
    assertEquals(2, page0.totalElements());
  }
}
