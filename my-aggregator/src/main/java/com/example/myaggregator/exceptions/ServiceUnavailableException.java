package com.example.myaggregator.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

import java.util.Locale;

public class ServiceUnavailableException extends ErrorResponseException {

    private final String serviceName;
    public ServiceUnavailableException(String serviceName) {
        super(HttpStatus.SERVICE_UNAVAILABLE);
        this.serviceName = serviceName;
    }


    @Override
    public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        String localizedClassName = messageSource.getMessage(this.serviceName, (Object[])null, this.serviceName, locale);
        return new Object[]{localizedClassName, this.serviceName};
    }
}
