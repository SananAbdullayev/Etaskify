package com.example.etaskifyiba.exception.handling;

public enum ErrorCodeEnum {
    UNKNOWN_ERROR(1000, "Unknown Error"),
    USER_NOT_FOUND(1001, "Can not find User with given id"),
    TASK_NOT_FOUND(1002, "Can not find Task with given id"),
    ORGANIZATION_NOT_FOUND(1003, "Can not find Organization with given id"),
    VALIDATION_ERROR(1004, " is not valid"),
    ACCESS_DENIED_ERROR(1005, "Access is Denied"),
    IO_EXCEPTION(1006, "io_exception"),
    ILLEGAL_ARGUMENT(1007, "illegal_argument"),
    USERNAME_NOT_FOUND(1008, "Can not find User with given username"),
    USERNAME_ALREADY_EXIST(1009, "Username is already exist"),
    EMAIL_ALREADY_EXIST(1009, "Email is already exist");


    private final String message;
    private final int code;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
