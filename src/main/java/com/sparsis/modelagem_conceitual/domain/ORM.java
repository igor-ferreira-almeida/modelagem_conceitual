package com.sparsis.modelagem_conceitual.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class ORM<T> {
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private T id;
	
	@Version
    private Integer version;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	public abstract ORM<T> prepareUpdate(ORM<T> orm);
}
