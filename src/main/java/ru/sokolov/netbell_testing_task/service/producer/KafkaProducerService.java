package ru.sokolov.netbell_testing_task.service.producer;

import ru.sokolov.netbell_testing_task.model.MessageKafkaDTO;

public interface KafkaProducerService {

    void send(MessageKafkaDTO message);
}
