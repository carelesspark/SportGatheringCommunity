package com.swithus.community.global.exception;

public class FolderCreationException extends RuntimeException{
    public FolderCreationException(String message) {
        super(message);
    }

    public FolderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
