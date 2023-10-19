package me.emate.matefront.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
    private static final String MESSAGE = "Unauthorized";

    public UnAuthorizedException() {
        super(MESSAGE);
    }
}