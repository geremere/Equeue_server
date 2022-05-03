package ru.hse.equeue.exception;

import ru.hse.equeue.exception.message.ExceptionMessage;

public class BaseException extends RuntimeException
{

    public BaseException(ExceptionMessage message)
    {
        super(message.getValue());
    }

}
