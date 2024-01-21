package co.mercy.cloudvendorcruddemo.exception;

import org.springframework.http.HttpStatus;

public class CloudVendorException {
    private final Object data;
    private final HttpStatus httpStatus;

    public CloudVendorException(Object data, HttpStatus httpStatus) {
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getData() {
        return data;
    }
}
