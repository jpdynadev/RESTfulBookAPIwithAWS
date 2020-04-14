package com.example.ModelImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoggerInter.LoggerInterface;


@RestController
@RequestMapping("/TestController")
public class BooksController {

	public static final LoggerInterface log = new LoggerInterface();
	
	@Autowired
	Books bookInit = new Books();
	
	//Add a book
	@PostMapping("/addBook")
	public ResponseEntity<BookResponse> addBook(@RequestBody Book book){
		try {
			log.info("Initializing addition of book: " + book.getName());
			bookInit.addBook(book);
			BookResponse response = new BookResponse("Success", book, "Book was successfully added");
			return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Error adding book: " + book.getName() + "\n" + e.getLocalizedMessage());
			BookResponse response = new BookResponse("Failed", book, "Book could not be added due to: " + e.getMessage());
			return new ResponseEntity<BookResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Get All Books
	@GetMapping("/getAllBooks")
	public ResponseEntity<BookResponse> getAllBooks(){
		try {
			log.info("Initializing getAllBooks");
			List<Book> bookList = bookInit.findAllBooks();
			BookResponse response = new BookResponse("Success", bookList, "Successfully retrieved List for all books");
			log.info("Successfully retrieved all books");
			return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Failed due to: " + e.getLocalizedMessage());
			return new ResponseEntity<BookResponse>(new BookResponse("Failed", "Failed due to: " + e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	//By Name
	@GetMapping("/bookByName/{name}")
	public ResponseEntity<BookResponse> getBookByName(@PathVariable String name){
		try {
			log.info("Starting fetch for: " + name);
			Book b = bookInit.byName(name);
			BookResponse result = new BookResponse("Success", b, "Successfully fetched " + name);
			if(b.getName().equalsIgnoreCase("Not found by name")) {
				log.debug("Could not find book for given name: " + name);
				result = new BookResponse("Failed", "Could not find book by given name");
				return new ResponseEntity<BookResponse>(result, HttpStatus.NOT_FOUND);
			}
			log.info("Successfully retrieved: " + b.toString());
			return new ResponseEntity<BookResponse>(result, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Error find book: " + name + " due to: " + e.getLocalizedMessage());
			BookResponse result = new BookResponse("Failed", "Unsuccessfully fetched due to: " + e.getMessage());
			return new ResponseEntity<BookResponse>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//By ID
	@GetMapping("/bookByID/{id}")
	public ResponseEntity<BookResponse> getBookByID(@PathVariable String id){
		try {
			log.info("Starting look up by id for: " + id);
			Book b = bookInit.byID(id);
			BookResponse response;
			if(b.getName().equalsIgnoreCase("Not found by id")) {
				log.debug("Could not find book using ID: " + id);
				response = new BookResponse("Failed", "Could not find book for given id: " + id);
				return new ResponseEntity<BookResponse>(response, HttpStatus.NOT_FOUND);
			}
			log.info("Successfully fetched: " + id);
			response = new BookResponse("Success", b, "Successfully retrieved book using ID");
			return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Could not find book using id: " + id + " due to: " + e.getLocalizedMessage()); 
			BookResponse response = new BookResponse("Failed", "Failed due to " + e.getMessage());
			return new ResponseEntity<BookResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Update Book
	//By ID
	@PutMapping("/updateBookByID")
	public ResponseEntity<BookResponse> updateBook(@RequestBody Book b){
		try {
			log.info("Starting update for :" + b.toString());
			String result = bookInit.updateBook(b);
			BookResponse response;
			if(result.equalsIgnoreCase("could not find book")) {
				log.debug("Could not find book ID: " + b.toString());
				response = new BookResponse("Failed", b, "Could not find book ID");
				return new ResponseEntity<BookResponse>(response, HttpStatus.NOT_FOUND);
			}
			log.info("Successfully completed update for " + b.toString());
			response = new BookResponse("Success", b, "Successfully updated book");
			return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Failed update for " + b.toString() + " due to: " + e.getMessage());
			BookResponse result = new BookResponse("Failed", "Failed due to: " + e.getMessage());
			return new ResponseEntity<BookResponse>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Delete Book
	@DeleteMapping("/deleteBook")
	public ResponseEntity<BookResponse> deleteBook(@RequestBody Book b){
		try {
			log.info("Deleting book " + b.toString());
			String result = bookInit.deleteBook(b.getSerialID());
			if(result.equalsIgnoreCase("Could not find book")) {
				log.debug("Could not delete book " + b.toString());
				BookResponse response = new BookResponse("Failed", b, "Could not find book");
				return new ResponseEntity<BookResponse>(response, HttpStatus.NOT_FOUND);
			}
			log.info("Ending fetch to delete book " + b.toString() + " successfully");
			BookResponse response = new BookResponse("Success", b, "successfully deleted book");
			return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("failed to delete book " + b.toString() + " due to " + e.getMessage());
			BookResponse response = new BookResponse("Failed", "failed due to: " + e.getMessage());
			return new ResponseEntity<BookResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
