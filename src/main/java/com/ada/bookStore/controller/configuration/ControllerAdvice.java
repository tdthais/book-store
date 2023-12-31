package com.ada.bookStore.controller.configuration;

import com.ada.bookStore.controller.exception.IdNotFoundError;
import com.ada.bookStore.controller.exception.PasswordValidationError;
import com.ada.bookStore.controller.exception.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ControllerAdvice {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationError> handler(MethodArgumentNotValidException exception){
        List<ValidationError> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach( e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationError validationError =  new ValidationError(e.getField(), message);
            errors.add(validationError);
        });

        return errors;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordValidationError.class)
    public String handlerPassword(PasswordValidationError exception){

        return exception.getDescription();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNotFoundError.class)
    public String handlerIdNotFound(IdNotFoundError exception){
        return exception.getDescription();
    }




}
