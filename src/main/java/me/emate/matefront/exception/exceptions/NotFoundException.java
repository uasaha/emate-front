package me.emate.matefront.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  private static final String MESSAGE = "Not Found";

  public NotFoundException() {
    super(MESSAGE);
  }
}
