package me.emate.matefront.member;

public class MemberNotFountException extends RuntimeException {

  private static final String MESSAGE = "찾을 수 없는 멤버입니다.";

  public MemberNotFountException() {
    super(MESSAGE);
  }
}
