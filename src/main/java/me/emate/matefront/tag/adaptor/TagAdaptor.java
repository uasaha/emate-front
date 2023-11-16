package me.emate.matefront.tag.adaptor;

import java.util.List;
import me.emate.matefront.tag.dto.TagListResponseDto;

public interface TagAdaptor {

  List<TagListResponseDto> getAllTags();
}
