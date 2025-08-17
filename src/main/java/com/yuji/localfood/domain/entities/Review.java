package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.yuji.localfood.domain.enums.ReviewAudience;
import com.yuji.localfood.domain.validations.Preconditions;

public final class Review {
  private final UUID id;
  private final UUID userId;
  private final UUID placeId;
  private final int rating;
  private final String comment; 
  private final String tipForForeigners; 
  private final ReviewAudience audience;
  private final Instant createdAt;
  private final Instant updatedAt;

  public Review(UUID id, UUID userId, UUID placeId, int rating, String comment,
      String tipForForeigners, ReviewAudience audience,
      Instant createdAt, Instant updatedAt) {

    this.id = Preconditions.requireNonNullArg(id, "id");
    this.userId = Preconditions.requireNonNullArg(userId, "userId");
    this.placeId = Preconditions.requireNonNullArg(placeId, "placeId");

    if (rating < 1 || rating > 5)
      throw new IllegalArgumentException("rating must be 1..5");
    this.rating = rating;

    this.comment = (comment == null || comment.isBlank()) ? null : comment.trim();

    this.audience = Objects.requireNonNull(audience, "audience");
    this.tipForForeigners = switch (audience) {
      case FOREIGNERS -> {
        if (tipForForeigners == null || tipForForeigners.isBlank())
          throw new IllegalArgumentException("tipForForeigners required for audience=FOREIGNERS");
        yield tipForForeigners.trim();
      }
      default -> (tipForForeigners == null || tipForForeigners.isBlank()) ? null : tipForForeigners.trim();
    };

    this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
    this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt");
    if (updatedAt.isBefore(createdAt))
      throw new IllegalArgumentException("updatedAt < createdAt");
  }

  // fábrica prática
  public static Review createGeneral(UUID userId, UUID placeId, int rating, String comment) {
    var now = Instant.now();
    return new Review(UUID.randomUUID(), userId, placeId, rating, comment, null,
        ReviewAudience.GENERAL, now, now);
  }

  public static Review createForForeigners(UUID userId, UUID placeId, int rating, String comment, String tip) {
    var now = Instant.now();
    return new Review(UUID.randomUUID(), userId, placeId, rating, comment, tip,
        ReviewAudience.FOREIGNERS, now, now);
  }

  public Review withUpdatedAt(Instant newUpdatedAt) {
    return new Review(id, userId, placeId, rating, comment, tipForForeigners, audience, createdAt, newUpdatedAt);
  }

  public UUID getId() {
    return id;
  }

  public UUID getUserId() {
    return userId;
  }

  public UUID getPlaceId() {
    return placeId;
  }

  public int getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }

  public String getTipForForeigners() {
    return tipForForeigners;
  }

  public ReviewAudience getAudience() {
    return audience;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Review other))
      return false;
    return id.equals(other.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    var shortComment = (comment == null) ? null : (comment.length() > 60 ? comment.substring(0, 60) + "…" : comment);
    return "Review{id=%s, userId=%s, placeId=%s, rating=%d, audience=%s, createdAt=%s, updatedAt=%s, comment=%s}"
        .formatted(id, userId, placeId, rating, audience, createdAt, updatedAt, shortComment);
  }
}
