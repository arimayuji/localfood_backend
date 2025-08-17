package com.yuji.localfood.domain.pagination;

import java.util.List;
import java.util.function.Function;

public final class Page<T> {
  private final List<T> content;
  private final long totalElements;
  private final int page;
  private final int size;

  private Page(List<T> content, long totalElements, int page, int size) {
    if (totalElements < 0)
      throw new IllegalArgumentException("totalElements >= 0");
    this.content = List.copyOf(content);
    this.totalElements = totalElements;
    this.page = page;
    this.size = size;
  }

  public static <T> Page<T> of(List<T> content, long totalElements, int page, int size) {
    return new Page<>(content, totalElements, page, size);
  }

  public List<T> content() {
    return content;
  }

  public long totalElements() {
    return totalElements;
  }

  public int page() {
    return page;
  }

  public int size() {
    return size;
  }

  public int totalPages() {
    return (int) Math.ceil(totalElements / (double) size);
  }

  public boolean hasNext() {
    return page + 1 < totalPages();
  }

  public boolean hasPrevious() {
    return page > 0;
  }

  public <R> Page<R> map(Function<T, R> mapper) {
    return new Page<>(
        content.stream().map(mapper).toList(),
        totalElements,
        page,
        size);
  }

  @Override
  public String toString() {
    return "Page{page=" + page + ", size=" + size +
        ", totalElements=" + totalElements +
        ", totalPages=" + totalPages() + '}';
  }
}
