package bg.duosoft.bpo.common.web.controller.advice;

import bg.duosoft.bpo.common.util.exception.ValidationErrorException;
import bg.duosoft.bpo.common.util.validation.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrorException(ValidationErrorException ex, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        log.error("[400 BAD_REQUEST [{} {}] {}", servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}