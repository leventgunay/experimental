package org.basesource.chatngo.controller;

import java.util.List;
import java.util.Map;

import org.basesource.chatngo.repository.MessageRepository;
import org.basesource.chatngo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.basesource.chatngo.domain.Message;

@RestController
public class MessageController {

    @Autowired
    private UserRepository users;

    @Autowired
    private MessageRepository messages;

    /*
     * This MessageMapping annotated method will be handled by
     * SimpAnnotationMethodMessageHandler and after that the Message will be
     * forwarded to Broker channel to be forwarded to the client via WebSocket
     */
    @MessageMapping("/all")
    @SendTo("/topic/all")
    public Message post(@Payload Message message) {
        if (ObjectUtils.isEmpty(message.getFrom().getId()) && !users.existsByName(message.getFrom().getName())) {
            message.setFrom(
                users.save(message.getFrom())
            );
        }

        messages.save(message);
        return message;
    }

    @RequestMapping("/latest")
    public List<Message> getChatHistory() {
        return messages.findAll(PageRequest.of(0, 20, Sort.by(Direction.DESC, "sent"))).getContent();
    }
}
