package com.chat.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.boot.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

}
