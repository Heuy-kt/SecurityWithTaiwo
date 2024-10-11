package com.heuy.kt.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlreadyExistException extends RuntimeException{
    private final String message;
    private final Integer status;

}
