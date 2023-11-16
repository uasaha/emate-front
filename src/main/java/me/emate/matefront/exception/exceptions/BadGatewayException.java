package me.emate.matefront.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {

  private static final String MESSAGE = "Bad Gateway";

  public BadGatewayException() {
    super(MESSAGE);
  }
}
