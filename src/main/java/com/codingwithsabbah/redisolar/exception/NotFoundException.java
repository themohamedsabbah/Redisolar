package com.codingwithsabbah.redisolar.exception;

public class NotFoundException extends GenericException {
    public NotFoundException(AbstractGenericError error) {
        super(error);
    }
}
