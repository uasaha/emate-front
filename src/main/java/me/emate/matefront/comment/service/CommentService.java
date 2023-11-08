package me.emate.matefront.comment.service;

import me.emate.matefront.comment.dto.CommentListResponseDto;
import me.emate.matefront.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;

import java.util.List;

public interface CommentService {
    List<CommentListResponseDto> getCommentsByContentsNo(Integer contentsNo);

    void registerAnonymousComment(CommentNoMemberRegisterRequestDto requestDto);

    void registerMemberComment(CommentMemberRegisterRequestDto requestDto);
}
