package com.chat.boot.controller;


import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.boot.domain.Message;
import com.chat.boot.exception.ResourceNotFoundException;
import com.chat.boot.repo.MessageRepo;


@RestController
@RequestMapping("message")
public class MessageController {
	
	private final MessageRepo messageRepo;
	
	@Autowired
	public MessageController(MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {
    	BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }

}
