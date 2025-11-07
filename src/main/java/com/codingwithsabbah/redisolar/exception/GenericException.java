package com.codingwithsabbah.redisolar.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericException extends RuntimeException{
    private AbstractGenericError error;

    protected GenericException(AbstractGenericError error) {
        super(
                error.getErrorCode()
        );
        this.error = error;
    }
}
