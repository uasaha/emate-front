package me.emate.matefront.token.exception;

public class NotLoginException extends RuntimeException {

  private static final String MESSAGE = "로그인되지 않았습니다.";

  public NotLoginException() {
    super(MESSAGE);
  }
}
