package me.emate.matefront.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageableResponse<T> {
    private List<T> contents;
    private int totalPages;
    private int current;
    private boolean hasPrevious;
    private boolean hasNext;
}
