package com.example.customers.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.Locale;
import java.util.UUID;

public class NotFoundResponseException extends ErrorResponseException {

    private final UUID id;
    private final Class objectClass;
    public NotFoundResponseException(UUID id, Class<?> objectClass) {
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
