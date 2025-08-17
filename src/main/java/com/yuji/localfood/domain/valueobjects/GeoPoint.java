package com.yuji.localfood.domain.valueobjects;

public record GeoPoint(double latitude, double longitude) {
  public GeoPoint {
    if (latitude < -90 || latitude > 90)   throw new IllegalArgumentException("invalid lat");
    if (longitude < -180 || longitude > 180) throw new IllegalArgumentException("invalid lng");
  }
}
