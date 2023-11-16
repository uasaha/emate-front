package me.emate.matefront.member;

public class NotAuthorizedException extends RuntimeException {

  private static final String MESSAGE = "NEED LOGIN";

  public NotAuthorizedException() {
    super(MESSAGE);
  }
}
