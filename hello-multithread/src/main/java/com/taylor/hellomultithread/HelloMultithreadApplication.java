package com.taylor.hellomultithread;

import com.taylor.hellomultithread.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HelloMultithreadApplication {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 10 ; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
    }
    public static void main(String[] args) {

        SpringApplication.run(HelloMultithreadApplication.class, args);
    }

}
