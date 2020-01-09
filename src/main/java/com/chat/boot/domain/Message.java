package com.chat.boot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Table
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(MessageViews.DefaultView.class)
	private Long id;
	
	@JsonView(MessageViews.DefaultView.class)
	private String text;
	
	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonView(MessageViews.FullView.class)
	private LocalDateTime created;
}
