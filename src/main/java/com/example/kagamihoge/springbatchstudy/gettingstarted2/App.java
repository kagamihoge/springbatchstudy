package com.example.kagamihoge.springbatchstudy.gettingstarted2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        System.exit(SpringApplication.exit(SpringApplication.run(App.class, args)));
    }
}
