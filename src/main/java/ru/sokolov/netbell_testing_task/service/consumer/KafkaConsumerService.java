package ru.sokolov.netbell_testing_task.service.consumer;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.sokolov.netbell_testing_task.model.Message;
import ru.sokolov.netbell_testing_task.model.MessageKafkaDTO;
import ru.sokolov.netbell_testing_task.service.message.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final MessageService messageService;

    @KafkaListener(
            topics = "topic-1",
            groupId = "group-1",
            containerFactory = "concurrentListenerContainerFactory")
    public void receive(
            @Payload List<MessageKafkaDTO> kafkaMessages,
            Acknowledgment acknowledgment
    ) {
        List<Message> validFactMessages = new ArrayList<>();
        for (MessageKafkaDTO kafkaMessage : kafkaMessages) {
            // пустой список фактических сообщений - коммит сообщения kafka
            if (kafkaMessage.getMessages().isEmpty()) {
                // todo в какой именно лог писать? приложения? брокера? сегмента?
                //  в двух последних случаях надо настроить log appender, по идее
                log.warn("Сообщений нет");
                acknowledgment.acknowledge();
                continue;
            }
            // сообщение кафки, в котором среди фактических сообщений
            // может быть сообщение с невалидным id, все равно коммитим.
            // сохраняем только валидные фактические.
            for (Message factMessage : kafkaMessage.getMessages()) {
                if (factMessage.getMessageId() < 0) {
                    log.warn("Отрицательный id");
                    continue;
                }
                validFactMessages.add(factMessage);
            }
            acknowledgment.acknowledge();
        }
        try {
            messageService.saveAll(validFactMessages);
        } catch (ConstraintViolationException constraintViolationException) {
            log.error("Ошибка сохранения в базу");
            constraintViolationException.printStackTrace();
        }
        // todo не успел добавить повторную попытку сохранения.
        //  должен быть handler, но в таком случае неясно, как откатывать
    }
}
