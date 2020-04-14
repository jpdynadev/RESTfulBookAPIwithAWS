package com.example.ModelImp;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="books")
public class Book {
	@NotBlank
	private String name;
	@Id @GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String serialID;
	@NotBlank
	private String description;
	public Book(String name, String serialID, String description) {
		super();
		this.name = name;
		this.serialID = serialID;
		this.description = description;
	}
	public Book(String name, String serialID) {
		super();
		this.name = name;
		this.serialID = serialID;
		this.description = "None Available";
	}
	public Book() {
		super();
		this.name = "";
		this.serialID = "";
		this.description = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", serialID=" + serialID + ", description=" + description + "]";
	}
	
}
