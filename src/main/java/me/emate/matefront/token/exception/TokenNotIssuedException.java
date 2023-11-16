package me.emate.matefront.token.exception;

public class TokenNotIssuedException extends RuntimeException {

  public static final String MESSAGE = "토큰이 정상적으로 발급되지 않았습니다.";

  public TokenNotIssuedException() {
    super(MESSAGE);
  }
}
