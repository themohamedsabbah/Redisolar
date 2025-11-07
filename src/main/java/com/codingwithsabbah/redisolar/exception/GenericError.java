package com.codingwithsabbah.redisolar.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenericError implements AbstractGenericError{
    NOT_FOUND("REDIS-NOT-FOUND", "REDIS-NOT-FOUND")
    ;

    private final String businessCode;
    private final String errorCode;
}
