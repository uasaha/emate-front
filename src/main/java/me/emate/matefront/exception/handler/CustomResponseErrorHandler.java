package me.emate.matefront.exception.handler;

import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
@Component
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        log.warn(statusCode.toString());

        if (statusCode.equals(HttpStatus.BAD_REQUEST)) {
            throw new BadRequestException();
        } else if (statusCode.equals(HttpStatus.UNAUTHORIZED)) {
            throw new UnAuthorizedException();
        } else if (statusCode.equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException();
        } else if (statusCode.equals(HttpStatus.FORBIDDEN)) {
            throw new NotLoginException();
        } else if (statusCode.is4xxClientError()) {
            throw new NotFoundException();
        } else if (statusCode.equals(HttpStatus.BAD_GATEWAY)) {
            throw new BadGatewayException();
        }
        else if (statusCode.is5xxServerError()) {
            throw new ServerErrorException();
        }
    }
}
