package me.emate.matefront.tag.service;

import me.emate.matefront.tag.dto.TagListResponseDto;

import java.util.List;

public interface TagService {
    List<TagListResponseDto> getAllTags();
}
