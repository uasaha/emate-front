package me.emate.matefront.comment.adaptor;

import java.util.List;
import me.emate.matefront.comment.dto.CommentListResponseDto;
import me.emate.matefront.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;

public interface CommentAdaptor {

  List<CommentListResponseDto> getCommentsByContentsNo(Integer contentsNo);

  void registerAnonymousComment(CommentNoMemberRegisterRequestDto requestDto);

  void registerMemberComment(CommentMemberRegisterRequestDto requestDto);
}
