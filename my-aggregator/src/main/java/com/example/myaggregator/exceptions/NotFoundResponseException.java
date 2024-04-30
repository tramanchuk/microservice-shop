package com.example.myaggregator.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

import java.util.Locale;

public class NotFoundResponseException extends ErrorResponseException {

    private final Long id;
    private final Class objectClass;
    public NotFoundResponseException(Long id, Class<?> objectClass) {
        super(HttpStatus.NOT_FOUND);
        this.id = id;
        this.objectClass = objectClass;
    }


    @Override
    public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        String localizedClassName = messageSource.getMessage(this.objectClass.getName(), (Object[])null, this.objectClass.getSimpleName().toLowerCase(), locale);
        return new Object[]{localizedClassName, this.id};
    }
}
