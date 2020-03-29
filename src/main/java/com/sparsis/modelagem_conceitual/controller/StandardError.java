package com.sparsis.modelagem_conceitual.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private LocalDateTime date;
	
	public StandardError(Integer status, String message, LocalDateTime date) {
		this.status = status;
		this.message = message;
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
