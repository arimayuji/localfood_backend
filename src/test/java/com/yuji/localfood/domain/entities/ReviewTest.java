package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.yuji.localfood.domain.enums.ReviewAudience;

public class ReviewTest {

  private final UUID uid = UUID.randomUUID();
  private final UUID pid = UUID.randomUUID();
  private final Instant now = Instant.parse("2023-01-01T00:00:00Z");

  @Test
  void shouldCreateValidGeneralReview() {
    var review = new Review(UUID.randomUUID(), uid, pid,
        5, "comment", null, ReviewAudience.GENERAL, now, now);

    assertEquals(5, review.getRating());
    assertEquals(ReviewAudience.GENERAL, review.getAudience());
    assertEquals("comment", review.getComment());
    assertNull(review.getTipForForeigners());
    assertEquals(now, review.getCreatedAt());
    assertEquals(now, review.getUpdatedAt());
  }

  @Test
  void shouldRejectRatingOutsideRange() {
    Exception ratingOutOfBoundsException = assertThrows(IllegalArgumentException.class,
        () -> new Review(UUID.randomUUID(), uid, pid, 0, "c", null, ReviewAudience.GENERAL, now, now));
    assertEquals("Rating must be between 1 and 5", ratingOutOfBoundsException.getMessage());

    Exception ratingOutOfBoundsException2 = assertThrows(IllegalArgumentException.class,
        () -> new Review(UUID.randomUUID(), uid, pid, 6, "c", null, ReviewAudience.GENERAL, now, now));
    assertEquals("Rating must be between 1 and 5", ratingOutOfBoundsException2.getMessage());
  }

  @Test
  void shouldRequireTipWhenAudienceIsForeigners() {
    Exception tipRequiredException = assertThrows(IllegalArgumentException.class,
        () -> new Review(UUID.randomUUID(), uid, pid, 5, "comment", null, ReviewAudience.FOREIGNERS, now, now));

    assertEquals("tipForForeigners required for audience=FOREIGNERS", tipRequiredException.getMessage());
  }

  @Test
  void shouldRejectUpdatedAtBeforeCreatedAt() {
    Exception updatedAtBeforeCreatedAtException = assertThrows(IllegalArgumentException.class,
        () -> new Review(UUID.randomUUID(), uid, pid, 5, "c", null, ReviewAudience.GENERAL, now, now.minusSeconds(1)));

    assertEquals("updatedAt must be after createdAt", updatedAtBeforeCreatedAtException.getMessage());
  }
}
