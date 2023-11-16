package me.emate.matefront.utils;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageableResponse<T> {

  private List<T> contents;
  private int totalPages;
  private int current;
  private boolean hasPrevious;
  private boolean hasNext;
}
