package ru.sokolov.netbell_testing_task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sokolov.netbell_testing_task.model.Message;
import ru.sokolov.netbell_testing_task.model.MessageKafkaDTO;
import ru.sokolov.netbell_testing_task.service.producer.KafkaProducerService;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
@Log4j2
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public void send() throws InterruptedException {
        // для проверок
        Random random = new Random();
        while (true) {
            kafkaProducerService.send(
                    new MessageKafkaDTO(
                            Collections.singletonList(
                                    Message.builder()
                                            .messageId(1)
                                            .payload(UUID.randomUUID().toString())
                                            .build()
                            )
                    )
            );
            kafkaProducerService.send(
                    new MessageKafkaDTO(
                            Collections.singletonList(
                                    Message.builder()
                                            .messageId(2)
                                            .payload(UUID.randomUUID().toString())
                                            .build()
                            )
                    )
            );
            kafkaProducerService.send(
                    new MessageKafkaDTO(
                            Collections.singletonList(
                                    Message.builder()
                                            .messageId(random.nextInt())
                                            .payload(UUID.randomUUID().toString())
                                            .build()
                            )
                    )
            );
            kafkaProducerService.send(
                    new MessageKafkaDTO(
                            Collections.singletonList(
                                    Message.builder()
                                            .messageId(random.nextInt())
                                            .payload(UUID.randomUUID().toString())
                                            .build()
                            )
                    )
            );
            Thread.sleep(11000);
        }
    }

    @PostMapping("/sendWithEmptyMessages")
    public void sendWithEmptyMessages() {
        // для проверок
        kafkaProducerService.send(new MessageKafkaDTO());
    }

    @PostMapping("/sendWithNegativeId")
    public void sendWithNegativeId() {
        // для проверок
        kafkaProducerService.send(
                new MessageKafkaDTO(
                        Collections.singletonList(
                                Message.builder()
                                        .messageId(-1)
                                        .payload(UUID.randomUUID().toString())
                                        .build()
                        )
                )
        );
    }
}
