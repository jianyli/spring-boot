package com.li.support.exception;

import com.li.support.enums.ErrorCodeEnum;

public class ServiceException extends RuntimeException {
    private ErrorCodeEnum errorCode;

    public ServiceException() {}

    public ServiceException(String msg) { super(msg); }

    public ServiceException(Throwable cause) {super(cause);}

    public ServiceException(ErrorCodeEnum errorCode) {
        super(errorCode.getValue());
        this.errorCode = errorCode;
    }
}
