package bg.duosoft.bpo.common.web.controller;

import bg.duosoft.bpo.common.util.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice(basePackages = "bg.duosoft.bpo")
public class ExceptionHandlingController {

    @ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Unauthorized")  // 401
    @ExceptionHandler(UnauthorizedException.class)
    public void unauthorized() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request")  // 400
    @ExceptionHandler(BadRequestException.class)
    public void badRequest() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request")  // 400
    @ExceptionHandler(ValidationErrorException.class)
    public void validationError() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Forbidden")  // 403
    @ExceptionHandler(ForbiddenException.class)
    public void forbidden() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error")  // 500
    @ExceptionHandler(InternalServerErrorException.class)
    public void internalServerError() {
        // Nothing to do
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Resource Not Found")  // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public void resourceNotFound() {
        // Nothing to do
    }

}
