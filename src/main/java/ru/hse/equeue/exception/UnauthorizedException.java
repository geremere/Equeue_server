package ru.hse.equeue.exception;


import ru.hse.equeue.exception.message.ExceptionMessage;

public class UnauthorizedException extends BaseException
{

    public UnauthorizedException(ExceptionMessage message)
    {
        super(message);
    }
}
