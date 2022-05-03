package ru.hse.equeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EqueueApplication {

    public static void main(String[] args) {


        SpringApplication.run(EqueueApplication.class, args);
    }

    public void some(){
        EqueueApplication equeueApplication = EqueueApplication.this;
    }

}
