package ru.readzero.exception.user;

public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
