package me.emate.matefront.comment.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.comment.adaptor.CommentAdaptor;
import me.emate.matefront.comment.dto.CommentListResponseDto;
import me.emate.matefront.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentAdaptor commentAdaptor;

    @Override
    public List<CommentListResponseDto> getCommentsByContentsNo(Integer contentsNo) {
        return commentAdaptor.getCommentsByContentsNo(contentsNo);
    }

    @Override
    public void registerAnonymousComment(CommentNoMemberRegisterRequestDto requestDto) {
        commentAdaptor.registerAnonymousComment(requestDto);
    }

    @Override
    public void registerMemberComment(CommentMemberRegisterRequestDto requestDto) {
        commentAdaptor.registerMemberComment(requestDto);
    }
}
