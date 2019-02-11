package com.myaudiolibrary.apirest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class ConflictException extends HttpClientErrorException {
    public ConflictException(HttpStatus statusCode) {
        super(statusCode);
    }

    public ConflictException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public ConflictException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public ConflictException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
