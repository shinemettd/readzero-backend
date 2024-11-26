package ru.readzero.exception.auth;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String message) {
        super(message);
    }
}