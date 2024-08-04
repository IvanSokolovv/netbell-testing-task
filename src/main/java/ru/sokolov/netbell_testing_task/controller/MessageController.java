package ru.sokolov.netbell_testing_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sokolov.netbell_testing_task.model.Message;
import ru.sokolov.netbell_testing_task.service.message.MessageService;

import java.util.List;

/**
 * Контроллер для работы с сообщениями
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/createAny")
    public Message createAny() {
        return messageService.createAny();
    }

    @GetMapping("/getAll")
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping("/getHello")
    public String getHello() {
        return "Hello World!";
    }

    @GetMapping("/get")
    public Message get(@RequestParam Integer id) {
        return messageService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        messageService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        messageService.deleteAll();
    }
}
