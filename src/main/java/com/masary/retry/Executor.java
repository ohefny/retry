package com.masary.retry;

import java.util.List;

public class Executor {

    private Retry retry;

    public <T> T get(Command<T> command){
        T obj = null;
        int i = 0;
        while(i <= retry.getMaxRetries()){
            try {
                obj = command.execute();
                break;
            }catch (RuntimeException e){
                tryHandleWith(e,retry);
            }finally{
                i++;
            }
        }
        return obj;
    }

    private boolean tryHandleWith(RuntimeException e,Retry plan) {
        Retry r = plan;
        for(;;){
            if(r == null)
                throw e;
            PreHandler preHandler = r.getPreHandler();
            List<Class> exceptionList = r.getExceptions();
            if(exceptionList.contains(e.getClass())){
                preHandler.handle();
                return true;
            }
            r = r.getSuccessor();
        }

    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }
}
