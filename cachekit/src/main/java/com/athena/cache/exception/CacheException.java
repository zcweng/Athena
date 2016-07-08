package com.athena.cache.exception;

/**
 * 缓存处理错误
 * @version 0.1 king 2016-03
 */
public class CacheException extends RuntimeException {

    public CacheException() {
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CacheException(Throwable throwable) {
        super(throwable);
    }

}
