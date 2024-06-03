package com.example.demo.counter;

import com.example.demo.counter.counter.CounterManager;
import com.example.demo.counter.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Counter {

    private final TestMapper testMapper;

    @Autowired
    public Counter(TestMapper testMapper){
        this.testMapper = testMapper;
    }


    @Bean
    public CounterManager counterManager(){
        return new CounterManager(testMapper);
    }
    
}
