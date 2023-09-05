package me.emate.matefront.token.exception;

public class TokenParseException extends RuntimeException {
    public static final String MESSAGE = "토큰을 읽어올 수 없습니다";

    public TokenParseException() {
        super(MESSAGE);
    }
}
