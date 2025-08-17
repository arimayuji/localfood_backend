package com.yuji.localfood.domain.pagination;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Sort {
  public enum Direction {
    ASC, DESC
  }

  public static final class Order {
    private final String property;
    private final Direction direction;

    private Order(String property, Direction direction) {
      if (property == null || property.isBlank()) {
        throw new IllegalArgumentException("Propriedade obrigatória");
      }
      this.property = property;
      this.direction = Objects.requireNonNull(direction);
    }

    public static Order asc(String property) {
      return new Order(property, Direction.ASC);
    }

    public static Order desc(String property) {
      return new Order(property, Direction.DESC);
    }

    public String property() {
      return property;
    }

    public Direction direction() {
      return direction;
    }
  }

  private final List<Order> orders;

  private Sort(List<Order> orders) {
    this.orders = List.copyOf(orders);
  }

  public static Sort by(Order... orders) {
    return new Sort(List.of(orders));
  }

  public static Sort unsorted() {
    return new Sort(Collections.emptyList());
  }

  public boolean isSorted() {
    return !orders.isEmpty();
  }

  public List<Order> orders() {
    return orders;
  }

  @Override
  public String toString() {
    return orders.toString();
  }
}
