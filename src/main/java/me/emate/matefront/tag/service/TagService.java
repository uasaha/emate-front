package me.emate.matefront.tag.service;

import java.util.List;
import me.emate.matefront.tag.dto.TagListResponseDto;

public interface TagService {

  List<TagListResponseDto> getAllTags();
}
