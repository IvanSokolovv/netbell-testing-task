package ru.sokolov.netbell_testing_task.service.message;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.sokolov.netbell_testing_task.model.Message;

import java.util.List;

/**
 * Сервис для работы с сообщениями
 * */
@Validated
public interface MessageService {

    Message createAny();

    Message create(Message message);

    List<Message> saveAll(List<Message> messages);

    List<Message> getAll();

    Message get(@NotNull Integer id);

    void delete(@NotNull Integer id);

    void deleteAll();
}
