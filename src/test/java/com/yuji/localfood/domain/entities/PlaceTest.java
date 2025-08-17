package com.yuji.localfood.domain.entities;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.yuji.localfood.domain.enums.PlaceCategory;
import com.yuji.localfood.domain.enums.PriceLevel;
import com.yuji.localfood.domain.valueobjects.GeoPoint;
import com.yuji.localfood.domain.valueobjects.PhoneNumber;

public class PlaceTest {
    private final UUID id = UUID.randomUUID();
    private final UUID userId = UUID.randomUUID();
    private final GeoPoint location = new GeoPoint(0, 0);
    private final Instant now = Instant.parse("2023-01-01T00:00:00Z");
    private final PhoneNumber phone = PhoneNumber.of("1234567890");

    @Test
    void shouldCreateValidPlace() {
        var place = new Place(id, "name", "description", "city", "country", location,
                PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                now, now);

        assertEquals(id, place.getId());
        assertEquals("name", place.getName());
        assertEquals("description", place.getDescription());
        assertEquals("city", place.getCity());
        assertEquals("country", place.getCountry());
        assertEquals(location, place.getLocation());
        assertEquals(PlaceCategory.CAFE, place.getCategory());
        assertEquals(PriceLevel.MODERATE, place.getPriceLevel());
        assertEquals(false, place.isLocalOwned());
        assertEquals(phone, place.getPhone());
        assertEquals("email", place.getEmail());
        assertEquals(userId, place.getCreatedBy());
        assertEquals(now, place.getCreatedAt());
        assertEquals(now, place.getUpdatedAt());
    }

    @Test
    void shouldRejectNullFields() {
        Exception nameNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, null, "description", "city", "country", location,
                        PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception descriptionNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", null, "city", "country", location,
                        PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception cityNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", "description", null, "country", location,
                        PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception countryNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", "description", "city", null, location,
                        PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception locationNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", "description", "city", "country", null,
                        PlaceCategory.CAFE, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception categoryNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", "description", "city", "country", location,
                        null, PriceLevel.MODERATE, false, phone, "email", userId,
                        now, now));
        Exception priceLevelNullException = assertThrows(IllegalArgumentException.class,
                () -> new Place(id, "name", "description", "city", "country", location,
                        PlaceCategory.CAFE, null, false, phone, "email", userId,
                        now, now));

        assertEquals("name", nameNullException.getMessage());
        assertEquals("description", descriptionNullException.getMessage());
        assertEquals("city", cityNullException.getMessage());
        assertEquals("country", countryNullException.getMessage());
        assertEquals("location", locationNullException.getMessage());
        assertEquals("category", categoryNullException.getMessage());
        assertEquals("priceLevel", priceLevelNullException.getMessage());
    }
}
