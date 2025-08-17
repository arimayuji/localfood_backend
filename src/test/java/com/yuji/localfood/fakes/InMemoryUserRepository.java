package com.yuji.localfood.fakes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.User;
import com.yuji.localfood.domain.pagination.Page;
import com.yuji.localfood.domain.pagination.PageRequest;
import com.yuji.localfood.domain.repositories.UserRepository;
import com.yuji.localfood.domain.valueobjects.Email;

public class InMemoryUserRepository implements UserRepository {

  private final Map<UUID, User> users = new HashMap<>();
  private final Map<String, UUID> emails = new HashMap<>();

  @Override
  public User create(User user) {
    final String emailKey = user.getEmail().value();

    if (emails.containsKey(emailKey)) {
      throw new IllegalArgumentException("Email already exists" + emailKey);
    }

    emails.put(emailKey, user.getId());
    users.put(user.getId(), user);
    return user;
  }

  @Override
  public User update(User user) {
    final UUID userId = user.getId();
    final User oldUser = users.get(userId);

    if (!users.containsKey(userId)) {
      throw new IllegalArgumentException("User not found" + userId);
    }

    final String oldKey = oldUser.getEmail().value();
    final String newKey = user.getEmail().value();

    if (!oldKey.equals(newKey)) {
      if(emails.containsKey(newKey)) {
        throw new IllegalArgumentException("Email already exists" + newKey);
      }
      emails.remove(oldKey);
      emails.put(newKey, userId);
    }
    
    users.put(userId, user);
    return user;
  }

  @Override
  public Optional<User> findById(UUID id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public Optional<User> findByEmail(Email email) {
    if(!emails.containsKey(email.value())) {
      return Optional.empty();
    }
    return findById(emails.get(email.value()));
  }

  @Override
  public boolean existsByEmail(Email email) {
    return emails.containsKey(email.value());
  }

  @Override
  public Page<User> findAll(PageRequest pageRequest) {
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public void delete(UUID id) {
    if (!users.containsKey(id)) {
      throw new IllegalArgumentException("User not found" + id);
    }

    emails.remove(users.get(id).getEmail().value());
    users.remove(id);
  }

}
