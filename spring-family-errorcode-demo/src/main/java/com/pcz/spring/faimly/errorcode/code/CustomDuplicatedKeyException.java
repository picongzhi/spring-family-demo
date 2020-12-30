package com.pcz.spring.faimly.errorcode.code;

import org.springframework.dao.DuplicateKeyException;

/**
 * @author picongzhi
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
