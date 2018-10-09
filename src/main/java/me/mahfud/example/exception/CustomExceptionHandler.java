package me.mahfud.example.exception;

import me.mahfud.example.api.model.Response;
import me.mahfud.example.api.model.ResponseError;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;



@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);

        List<String> errors = new ArrayList<>();
        errors.add("Lol! I'm trying to custom error message for method argument not valid");
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
//        return handleExceptionInternal();
    }

    @ExceptionHandler(BusNotFoundException.class)
    public ResponseEntity<ResponseError> resourceNotFound(BusNotFoundException ex) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMessageException.class)
    public ResponseEntity<ApiError> noSuchMessageException(NoSuchMessageException ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generalException(NoSuchMessageException ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", "There's something wrong");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> constraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "User Error", errors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
