package ru.sokolov.netbell_testing_task.service.message;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolov.netbell_testing_task.model.Message;
import ru.sokolov.netbell_testing_task.repository.MessageRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public Message createAny() {
        return create(
                Message.builder()
                        .payload(UUID.randomUUID().toString())
                        .build()
        );
    }

    @Override
    @Transactional
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> saveAll(List<Message> messages) {
        return messageRepository.saveAll(messages);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Message get(@NotNull Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        messageRepository.deleteAll();
    }
}
