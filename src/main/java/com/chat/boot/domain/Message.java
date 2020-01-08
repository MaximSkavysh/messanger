package com.chat.boot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Message {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String text;
}
