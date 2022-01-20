package ru.bellintegrator.simpleservice.common.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.simpleservice.common.Dto.ErrorDto;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public Object unhandledException(Exception e){
        ErrorDto error = new ErrorDto();
        error.setError(e.toString());
        return error;
    }
}
