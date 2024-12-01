package ru.readzero.aop;

import ru.readzero.enums.session.SessionActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleUserMetadata {

    SessionActionType actionType();

}
