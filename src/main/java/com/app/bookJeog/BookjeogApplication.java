package com.app.bookJeog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookjeogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookjeogApplication.class, args);
    }

}
