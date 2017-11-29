package com.masary.retry;

public interface Command<T> {

    T execute();

}
