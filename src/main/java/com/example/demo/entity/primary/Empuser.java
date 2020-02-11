package com.example.demo.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="empuser")
public class Empuser {
	@Id
	@Column(name="name")
	private String name;

	@Column(name="age")
	private Integer age;
}
