package com.reepay.examples.subscription.model.exceptions;

import com.reepay.examples.subscription.model.ErrorResponse;

/**
 * Created by mikkel on 11/05/2017.
 */
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Optional service level error code. Only relevant for service level errors.
     */
    private final Integer code;

    /**
     * HTTP error code to use for this exception
     */
    private final int httpStatus;

    /**
     * Mandatory message. Application level or HTTP reason phrase.
     */
    private final String errorMessage;

    /**
     * Optional detailed error message
     */
    private final String detailedMessage;
    

    protected ServiceException(Integer code, int httpStatus, String errorMessage, String detailedMessage, Throwable cause) {
        super(cause);
        this.code = code;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.detailedMessage = detailedMessage;
    }

    public Integer getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        if (detailedMessage != null) {
            return detailedMessage;
        }
        if (getCause() != null) {
            return getCause().getMessage();
        }
        return errorMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }
    

    public static ServiceException create(Integer code, int httpStatus, String errorMessage, String detailedMessage,
                                          Throwable cause) {
        return new ServiceException(code, httpStatus, errorMessage, detailedMessage, cause);
    }

    public static ServiceException create(ErrorResponse error) {
        return create(error.getCode(), error.getHttpStatus(), error.getError(), error.getMessage(), null);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceException [code=").append(code).append(", httpStatus=").append(httpStatus).append(", errorMessage=")
                .append(errorMessage).append(", detailedMessage=").append(detailedMessage).append("]");
        return builder.toString();
    }
}
