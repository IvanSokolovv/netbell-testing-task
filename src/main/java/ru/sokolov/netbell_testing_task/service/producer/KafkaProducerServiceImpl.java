package ru.sokolov.netbell_testing_task.service.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.sokolov.netbell_testing_task.model.MessageKafkaDTO;

@Service
@Log4j2
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    @Qualifier("messageKafkaTemplate")
    private KafkaTemplate<String, MessageKafkaDTO> kafkaTemplate;

    @Override
    public void send(MessageKafkaDTO message) {
        kafkaTemplate.send("topic-1", message);
    }
}
