package me.emate.matefront.member.service;

public interface MemberService {
    boolean idConflictCheck(String id);

    boolean isNickConflict(String nickname);
}
