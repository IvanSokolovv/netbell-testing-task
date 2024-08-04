package ru.sokolov.netbell_testing_task;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class NetbellTestingTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetbellTestingTaskApplication.class, args);
    }
}
