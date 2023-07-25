package me.emate.matefront.tag.adaptor;

import me.emate.matefront.tag.dto.TagListResponseDto;

import java.util.List;

public interface TagAdaptor {
    List<TagListResponseDto> getAllTags();
}
