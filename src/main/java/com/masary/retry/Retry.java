package com.masary.retry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Retry {

    private int maxRetries = 1;
    private List<Class> exceptions = new ArrayList<>();
    private Retry successor;
    private PreHandler preHandler;

    public Retry on(Class... classes){
        this.exceptions.addAll(Arrays.asList(classes));
        return this;
    }

    public Retry withMaxRetries(int maxRetries){
        assert maxRetries > -1;
        this.maxRetries = maxRetries;
        return this;
    }


    public Retry preHandler(PreHandler preHandler){
        this.preHandler = preHandler;
        return this;
    }


    public Retry escalateTo(Retry retry){
        this.successor = retry;
        return this;
    }

    public PreHandler getPreHandler() {
        return preHandler;
    }

    public Retry getSuccessor() {
        return successor;
    }

    public List<Class> getExceptions() {
        return exceptions;
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}
