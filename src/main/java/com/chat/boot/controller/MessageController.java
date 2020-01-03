package com.chat.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("message")
public class MessageController {

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {
                {
                    put("id", "1");
                    put("text", "First Message");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("id", "2");
                    put("text", "Second Message");
                }
            });
            add(new HashMap<String, String>() {
                {
                    put("id", "3");
                    put("text", "Therd Message");
                }
            });
        }
    };

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream().filter(message -> message.get("id").equals(id)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
    }

    @PostMapping
    public Map<String, String> addMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(messages.size() + 1));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> oldMessage = getMessage(id);
        oldMessage.putAll(message);
        return message;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        System.out.println(id);
        messages.remove(message);
    }

}
