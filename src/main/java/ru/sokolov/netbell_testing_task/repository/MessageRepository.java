package ru.sokolov.netbell_testing_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sokolov.netbell_testing_task.model.Message;

/**
 * Репозиторий для работы с сообщениями
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
