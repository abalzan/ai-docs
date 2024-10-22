package com.andrei.aidocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class AiDocsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiDocsApplication.class, args);
    }

}
