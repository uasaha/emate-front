package me.emate.matefront.contents.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.adaptor.ContentsAdaptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {
    private final ContentsAdaptor contentsAdaptor;
}
