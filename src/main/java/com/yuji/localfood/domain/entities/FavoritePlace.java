package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.yuji.localfood.domain.validations.Preconditions;

public final class FavoritePlace {
  private final UUID userId;
  private final UUID placeId;
  private final Instant createdAt;
  
  public FavoritePlace(UUID userId, UUID placeId, Instant createdAt) {
    this.userId = Preconditions.requireNonNullArg(userId, "userId");
    this.placeId = Preconditions.requireNonNullArg(placeId, "placeId");
    this.createdAt = Preconditions.requireNonNullArg(createdAt, "createdAt");
  }

  public UUID getUserId() {
    return userId;
  }

  public UUID getPlaceId() {
    return placeId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof FavoritePlace other))
      return false;
    return userId.equals(other.userId) && placeId.equals(other.placeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, placeId);
  }

  @Override
  public String toString() {
    return "FavoritePlace [userId=" + userId + ", placeId=" + placeId + ", createdAt=" + createdAt + "]";
  }
}
