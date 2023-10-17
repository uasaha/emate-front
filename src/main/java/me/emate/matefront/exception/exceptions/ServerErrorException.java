package me.emate.matefront.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
    private static final String MESSAGE = "500, internal server error";

    public ServerErrorException() {
        super(MESSAGE);
    }
}
