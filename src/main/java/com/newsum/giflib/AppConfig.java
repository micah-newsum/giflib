package com.newsum.giflib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan //instructs framework to scan packages for controllers
public class AppConfig
{
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class,args);
    }
}
