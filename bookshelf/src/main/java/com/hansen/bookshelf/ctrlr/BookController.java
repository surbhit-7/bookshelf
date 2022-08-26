package com.hansen.bookshelf.ctrlr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hansen.bookshelf.model.Book;
import com.hansen.bookshelf.srvc.BookSrvc;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookSrvc bookSrvc;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Book bookToBeCreated) {
		ResponseEntity<Object> bookResponse;
		
		
		Book book = bookSrvc.createBook(bookToBeCreated);
		
		if(book !=null) {
			bookResponse = new ResponseEntity<Object>(book, null, HttpStatus.CREATED);
			return bookResponse;
		}else {
			String response = "Cant add as the key is already present";
			
			bookResponse = new ResponseEntity<Object>(response, null, HttpStatus.BAD_REQUEST);
			return bookResponse;
		}
	}

	@RequestMapping(value = "{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Object> read(@PathVariable(value = "bookId") Integer bookId) {
		ResponseEntity<Object> bookResponse;
		
		
		Book book = bookSrvc.getBook(bookId);
		
		if(book != null)
		{

			bookResponse = new ResponseEntity<Object>(book, null, HttpStatus.OK);
			return bookResponse;
		}else {
			String response = "Key not found";
			
			bookResponse = new ResponseEntity<Object>(response, null, HttpStatus.NOT_FOUND);
			return bookResponse;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Book>> readAll() {
		ResponseEntity<List<Book>> bookResponse;
		
		
		List<Book> bookList = bookSrvc.getAllBooks();
		
		
		bookResponse = new ResponseEntity<List<Book>>(bookList, null, HttpStatus.OK);
		return bookResponse;
	}

	@RequestMapping(method = RequestMethod.PATCH) //OR PUT
	public ResponseEntity<Object> update(@RequestBody Book bookToBeUpdated) {
		ResponseEntity<Object> bookResponse;
		
		
		Book book = bookSrvc.updateBook(bookToBeUpdated);
		
		if(book != null) {
			bookResponse = new ResponseEntity<Object>(book, null, HttpStatus.OK);
			return bookResponse;
		}else {
			String response = "Key not found";
			
			bookResponse = new ResponseEntity<Object>(response, null, HttpStatus.NOT_FOUND);
			return bookResponse;
		}
	}

	@RequestMapping(value = "{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable(value = "bookId") Integer bookId) {
		ResponseEntity<String> bookResponse;
		
		
		String isdeleted = bookSrvc.deleteBook(bookId);
		
		
		
		if (isdeleted.length() != 0) {
			bookResponse = new ResponseEntity<String>(isdeleted, null, HttpStatus.OK);
		} else {
			String response  = "The given key is not present";
			
			bookResponse = new ResponseEntity<String>(response, null, HttpStatus.NOT_FOUND);
		}
		return bookResponse;
	}

}
