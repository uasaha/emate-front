package me.emate.matefront.exception.advice;

import me.emate.matefront.exception.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionAdvice {
    @ExceptionHandler(BadRequestException.class)
    public String error400() {
        return "error/400";
    }

    @ExceptionHandler(NotFoundException.class)
    public String error404() {
        return "error/404";
    }

    @ExceptionHandler(NotLoginException.class)
    public String errorLogin() {
        return "redirect:/login";
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String error401() {
        return "error/401";
    }

    @ExceptionHandler(ServerErrorException.class)
    public String error500() {
        return "error/500";
    }

    @ExceptionHandler(BadGatewayException.class)
    public String error502() {
        return "error/502";
    }

    @ExceptionHandler(RuntimeException.class)
    public String errorOther() {
        return "error/500";
    }
}
