package com.yuji.localfood.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import com.yuji.localfood.domain.entities.User;
import com.yuji.localfood.domain.pagination.Page;
import com.yuji.localfood.domain.pagination.PageRequest;
import com.yuji.localfood.domain.valueobjects.Email;

public interface UserRepository {
  User create(User user);

  User update(User user);

  Optional<User> findById(UUID id);

  Optional<User> findByEmail(Email email);

  boolean existsByEmail(Email email);

  Page<User> findAll(PageRequest pageRequest);

  void delete(UUID id);
}
