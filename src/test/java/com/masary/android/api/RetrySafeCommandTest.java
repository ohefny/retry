package com.masary.android.api;

import com.masary.retry.Command;
import com.masary.retry.PreHandler;
import com.masary.retry.Retry;
import com.masary.retry.SafeCommand;
import org.junit.Assert;
import org.junit.Test;


public class RetrySafeCommandTest {


    @Test
    public void test1(){
        Retry retry = new Retry().withMaxRetries(1).preHandler(new PreHandler() {
            @Override
            public void handle() {
                System.out.println("done");
            }
        }).on(RuntimeException.class);

        Long l = SafeCommand.with(retry).get(new Command<Long>() {
            @Override
            public Long execute() {
                return 1L;
            }
        });


        Assert.assertTrue(1L == l);
    }



    @Test
    public void test2(){
        Retry retry = new Retry().withMaxRetries(1).preHandler(new PreHandler() {
            @Override
            public void handle() {
                System.out.println("done");
            }
        }).on(RuntimeException.class);

        Long l = SafeCommand.with(retry).get(new Command<Long>() {
            int i = 0;
            @Override
            public Long execute() {
                i++;
                if(i == 1)
                    throw new RuntimeException();
                else return 1L;
            }
        });

        Assert.assertTrue(1L == l);
    }
}
