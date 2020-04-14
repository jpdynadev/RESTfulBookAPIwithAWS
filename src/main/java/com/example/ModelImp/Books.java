package com.example.ModelImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class Books {
	
	private List<Book> books;
	
	public Books() {
		this.books = new ArrayList<>();
	}
	
	//GetAll
	public List<Book> findAllBooks(){
		return books;
	}
	//GetOne
	//ByName
	public Book byName(String name) {
		for(Book b : books) {
			if(b.getName().equalsIgnoreCase(name)) {
				return b;
			}
		}
		return new Book("Not found by name", "N/A");
	}
	//ByID
	public Book byID(String id) {
		for(Book b : books) {
			if(b.getSerialID() == id) {
				return b;
			}
		}
		return new Book("Not found by id", "N/A");
	}
	
	//Update book
	//By ID
	public String updateBook(Book s) {
		for(Book b : books) {
			if(b.getSerialID().equalsIgnoreCase(s.getSerialID())) {
				b.setName(s.getName());
				b.setDescription(s.getDescription());
				return "Success";
			}
		}
		return "could not find book";
	}
	
	//Add Book
	public void addBook(Book b) {
		books.add(b);
	}
	
	//Delete Book
	//By Id
	public String deleteBook(String serial) {
		System.out.println(serial);
		for(Book b : books){
			System.out.println(b.toString());
			if(b.getSerialID().equalsIgnoreCase(serial)) {
				books.remove(b);
				return "Successful";
			}
		}
		return "Could not find book";
	} 
}
