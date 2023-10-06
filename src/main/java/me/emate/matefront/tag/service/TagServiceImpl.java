package me.emate.matefront.tag.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.tag.adaptor.TagAdaptor;
import me.emate.matefront.tag.dto.TagListResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagAdaptor tagAdaptor;

    @Cacheable(value = "tags")
    @Override
    public List<TagListResponseDto> getAllTags() {
        return tagAdaptor.getAllTags();
    }
}
