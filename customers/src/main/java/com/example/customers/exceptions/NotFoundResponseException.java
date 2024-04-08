package com.example.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

public class NotFoundResponseException extends ErrorResponseException {

    private static final String ERROR_CODE_URI = "https://mysite.com/error-code";
    private static final String ERROR_MESSAGE = "%s with id=%s is not found";
    private static final String ERROR_CODE = "%s-not-found";

    public NotFoundResponseException(String id, Class objectClass) {
        super(HttpStatus.NOT_FOUND);
        setTitle(getTitle(objectClass));
        setDetail(getDetail(id, objectClass));
        setTypeFromErrorCode(getErrorCode(objectClass));
    }

    private static String getErrorCode(Class objectClass) {
        return String.format(ERROR_CODE, objectClass.getSimpleName().toLowerCase());
    }

    private static String getDetail(String id, Class objectClass) {
        return String.format(ERROR_MESSAGE, objectClass.getSimpleName(), id);
    }

    private static String getTitle(Class objectClass) {
        return objectClass.getSimpleName() + " not found.";
    }

    private void setTypeFromErrorCode(String errorCode) {
        URI type = new DefaultUriBuilderFactory()
                .uriString(ERROR_CODE_URI)
                .pathSegment(errorCode)
                .build();
        setType(type);
    }
}
