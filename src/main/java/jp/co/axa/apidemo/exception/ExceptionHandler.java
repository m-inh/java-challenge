package jp.co.axa.apidemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Priority;

@Priority(1)
@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Object> handleException(final Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleException(final NotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> handleException(final UnauthorizedException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
    }
}
