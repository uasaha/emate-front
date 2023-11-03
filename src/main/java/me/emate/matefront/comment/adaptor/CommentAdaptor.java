package me.emate.matefront.comment.adaptor;

import me.emate.matefront.comment.dto.CommentListResponseDto;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;

import java.util.List;

public interface CommentAdaptor {
    List<CommentListResponseDto> getCommentsByContentsNo(Integer contentsNo);

    void registerAnonymousComment(CommentNoMemberRegisterRequestDto requestDto);
}
