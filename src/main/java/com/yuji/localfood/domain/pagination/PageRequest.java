package com.yuji.localfood.domain.pagination;

public final class PageRequest {
  private final int page; 
  private final int size; 
  private final Sort sort;

  private PageRequest(int page, int size, Sort sort) {
    if (page < 0)
      throw new IllegalArgumentException("page >= 0");
    if (size <= 0 || size > 200)
      throw new IllegalArgumentException("size between 1 and 200");

    this.page = page;
    this.size = size;
    this.sort = sort == null ? Sort.unsorted() : sort;
  }

  public static PageRequest of(int page, int size) {
    return new PageRequest(page, size, null);
  }

  public static PageRequest of(int page, int size, Sort sort) {
    return new PageRequest(page, size, sort);
  }

  public int page() {
    return page;
  }

  public int size() {
    return size;
  }

  public Sort sort() {
    return sort;
  }

  public long offset() {
    return (long) page * size;
  }

  @Override
  public String toString() {
    return "PageRequest{page=" + page + ", size=" + size + ", sort=" + sort + '}';
  }
}
