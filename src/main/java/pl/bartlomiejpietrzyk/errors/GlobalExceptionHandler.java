package pl.bartlomiejpietrzyk.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(RuntimeException.class);

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        LOG.error("RUNTIME EXCEPTION: " + e.getMessage());
        return "404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException ex) {
        return "404";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException e) {
        LOG.error("Resource not found: " + e.getMessage());
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String handleExceptionError(Exception e) {
        LOG.error("Exception error: " + e.getMessage());
        return "404";
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String fileNotFound(FileNotFoundException e) {
        LOG.error("File not found: " + e.getMessage());
        return "404";
    }
}
