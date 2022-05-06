package com.ex.reimbursementapi.exceptions;

public class CannotSendEmailWithoutReviewException extends RuntimeException {

    public CannotSendEmailWithoutReviewException() {
    }

    public CannotSendEmailWithoutReviewException(String message) {
        super(message);
    }

    public CannotSendEmailWithoutReviewException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotSendEmailWithoutReviewException(Throwable cause) {
        super(cause);
    }

    public CannotSendEmailWithoutReviewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

