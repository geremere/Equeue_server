package ru.hse.equeue.model.base;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {
    T getId();
}
