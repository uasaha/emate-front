package me.emate.matefront.tag.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.tag.adaptor.TagAdaptor;
import me.emate.matefront.tag.dto.TagListResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagAdaptor tagAdaptor;

    @Override
    public List<TagListResponseDto> getAllTags() {
        return tagAdaptor.getAllTags();
    }
}
