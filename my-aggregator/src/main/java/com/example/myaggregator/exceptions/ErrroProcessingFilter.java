package com.example.myaggregator.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class ErrroProcessingFilter {
    public static ExchangeFilterFunction errorHandler() {
        return null;
//        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            if (clientResponse.statusCode().is5xxServerError()) {
//                return clientResponse.bodyToMono(String.class)
//                        .flatMap(errorBody -> Mono.error(new ErrorResponseException(errorBody)));
//            } else if (clientResponse.statusCode().is4xxClientError()) {
//                return clientResponse.bodyToMono(String.class)
//                        .flatMap(errorBody -> Mono.error(new ErrorResponseException(errorBody)));
//            } else {
//                return Mono.just(clientResponse);
//            }
//        });
    }
}
