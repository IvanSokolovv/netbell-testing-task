package ru.sokolov.netbell_testing_task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ДТО сообщения
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageKafkaDTO {

    private List<Message> messages = new ArrayList<>();
}
