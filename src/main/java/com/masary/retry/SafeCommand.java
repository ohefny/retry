package com.masary.retry;

public class SafeCommand {


    public static Executor with(Retry retry){
        Executor executor = new Executor();
        executor.setRetry(retry);
        return executor;
    }

}
