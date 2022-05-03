package ru.hse.equeue.exception;


import ru.hse.equeue.exception.message.ExceptionMessage;

public class NotFoundException extends BaseException
{

    public NotFoundException(ExceptionMessage message)
    {
        super(message);
    }
}
