package uk.co.roteala.javaprocessor.exceptions;

public class TableException extends ApplicationRuntimeException {
    public TableException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TableException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
