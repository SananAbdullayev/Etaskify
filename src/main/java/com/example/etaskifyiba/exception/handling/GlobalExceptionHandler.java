package com.example.etaskifyiba.exception.handling;

import com.example.etaskifyiba.exception.AlreadyExistException;
import com.example.etaskifyiba.exception.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.USERNAME_NOT_FOUND.getCode())
                .message(ErrorCodeEnum.USERNAME_NOT_FOUND.getMessage())
                .build();
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(CustomNotFoundException e) {
        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String paramName = e.getParameter().getParameterName();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(paramName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.ACCESS_DENIED_ERROR.getCode())
                .message(ErrorCodeEnum.ACCESS_DENIED_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String fieldName = Objects.requireNonNull(e.getBindingResult().getFieldError()).getField();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(fieldName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleUnknownException(Exception e) {
//        return ErrorResponse.builder()
//                .code(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
//                .message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage())
//                .build();
//    }

    @ExceptionHandler(IOException.class)
    public ErrorResponse handleIOException(IOException e) {
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.IO_EXCEPTION.getCode())
                .message(ErrorCodeEnum.IO_EXCEPTION.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handingIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.ILLEGAL_ARGUMENT.getCode())
                .message(ErrorCodeEnum.ILLEGAL_ARGUMENT.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ErrorResponse handleAlreadyExistException(AlreadyExistException e) {
        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }


}
