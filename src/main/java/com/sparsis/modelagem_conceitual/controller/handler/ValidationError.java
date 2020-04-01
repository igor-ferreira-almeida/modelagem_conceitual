package com.sparsis.modelagem_conceitual.controller.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, LocalDateTime date) {
		super(status, message, date);
	}

	public List<FieldMessage> getErrors() {
		return Collections.unmodifiableList(errors);
	}

	public void addError(String name, String message) {
		this.errors.add(new FieldMessage(name, message));
	}
}
