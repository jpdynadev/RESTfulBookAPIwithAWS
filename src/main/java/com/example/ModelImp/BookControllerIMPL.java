package com.example.ModelImp;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoggerInter.LoggerInterface;
import com.example.Repo.BookRepository;

@RestController
@RequestMapping("/")
public class BookControllerIMPL {

	static LoggerInterface log = new LoggerInterface();
	
	@Autowired
	BookRepository br;
	
	//Return all books
	@GetMapping("/all")
	public ResponseEntity<BookResponse> getAll(){
		try {
			log.info("Starting fetch for all books");
			return new ResponseEntity<BookResponse>(new BookResponse("Success", br.findAll(), "Sucessfully found all books"), HttpStatus.OK);
		}catch (Exception e) {
			log.error("Failed due to: " + e.getMessage()); 
			return new ResponseEntity<BookResponse>(new BookResponse("Failed", "failed due to " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Add one book
	@PostMapping("/add")
	public ResponseEntity<BookResponse> addBook(@Valid @RequestBody Book b) {
		try {
			log.info("Starting add for book: " + b.toString());
			Book bk = br.save(b);
			if(bk == null) {
				log.debug("Failed to add book: " + b.toString());
				return new ResponseEntity<BookResponse>(new BookResponse("Failed", bk, "could not add book"), HttpStatus.BAD_REQUEST);
			}
			log.info("Ending add request for: " + b.toString());
			return new ResponseEntity<BookResponse>(new BookResponse("Success", bk , "Sucessfully added book"), HttpStatus.OK);
		}catch (Exception e) {
			log.error("Failed due to: " + e.getMessage()); 
			return new ResponseEntity<BookResponse>(new BookResponse("Failed",b, "failed due to " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Return one book using ID
	@GetMapping("/book/{id}")
	public ResponseEntity<BookResponse> getBook(@PathVariable(value = "id") String id) {
		try {
			log.info("Starting get for id: " + id);
			Book bk = br.findById(id).orElse(null);
			if(bk == null) {
				log.debug("Failed to get book for id: " + id);
				return new ResponseEntity<BookResponse>(new BookResponse("Failed", bk, "could not get book"), HttpStatus.BAD_REQUEST);
			}
			log.info("Ending fetch for book: " + bk);
			return new ResponseEntity<BookResponse>(new BookResponse("Success", bk, "succesfully fetched book"), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			log.error("Failed due to: " + e.getMessage()); 
			return new ResponseEntity<BookResponse>(new BookResponse("Failed",id, "failed due to " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Update one book
	@PutMapping("/updateBook")
	public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody Book b){
		try {
			log.info("Starting update for: " + b.toString()); 
			Book book = br.findById(b.getSerialID()).orElse(null);
			if(book == null) {
				log.debug("Could not update book " + b.toString());
				return new ResponseEntity<BookResponse>(new BookResponse("Failed", b, "Could not update book"), HttpStatus.BAD_REQUEST);
			}
			book.setName(b.getName());
			book.setDescription(b.getDescription());
			book.setSerialID(b.getSerialID());
			Book updated = br.save(book);
			return new ResponseEntity<BookResponse>(new BookResponse("Success",updated,"Successfully update book"), HttpStatus.OK);
		}catch (Exception e) {
			log.error("Failed due to: " + e.getMessage()); 
			return new ResponseEntity<BookResponse>(new BookResponse("Failed", b , "failed due to " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Delete book
	@DeleteMapping("/delete")
	public  ResponseEntity<BookResponse> deleteBook(@Valid @RequestBody Book b){
		try {
			log.info("Starting update for: " + b.toString()); 
			Book book = br.findById(b.getSerialID()).orElse(null);
			if(book == null) {
				log.debug("Could not update book " + b.toString());
				return new ResponseEntity<BookResponse>(new BookResponse("Failed", b, "Could not update book"), HttpStatus.BAD_REQUEST);
			}
			book.setName(b.getName());
			book.setDescription(b.getDescription());
			book.setSerialID(b.getSerialID());
			Book deleted = br.save(book);
			return new ResponseEntity<BookResponse>(new BookResponse("Success",deleted,"Successfully deleted book"), HttpStatus.OK);
		}catch (Exception e) {
			log.error("Failed due to: " + e.getMessage()); 
			return new ResponseEntity<BookResponse>(new BookResponse("Failed", b , "failed due to " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

