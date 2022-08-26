package com.hansen.bookshelf.srvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hansen.bookshelf.model.Book;

@Service
public class BookSrvc {

	private static HashMap<Integer, Book> bookMap = new HashMap<Integer, Book>();

	public Book createBook(Book bookToBeCreated) {
		if(bookMap.containsKey(bookToBeCreated.getId()) == true)
			return null;	
			
		bookMap.put(bookToBeCreated.getId(), bookToBeCreated);
		return bookToBeCreated;
	}

	public Book getBook(Integer bookId) {
		if(bookMap.containsKey(bookId)) {
			return bookMap.get(bookId);
		}else {
			return null;
		}
	}

	public List<Book> getAllBooks() {
		List<Book> bookList = new ArrayList<Book>(bookMap.values());
		return bookList; 
	}

	public Book updateBook(Book bookToBeUpdated) {
		if(bookMap.containsKey(bookToBeUpdated.getId()) == true) {
			bookMap.put(bookToBeUpdated.getId(), bookToBeUpdated);
			return bookToBeUpdated;
		}else
			return null;
	}

	public String deleteBook(Integer bookId) {

		//TODO Homework: Write code to delete bookId from bookMap(hashmap)

		if(bookMap.containsKey(bookId)) {
			bookMap.remove(bookId);
			
			return "The given id is succesfully Deleted";
		}else {
			
			return "";
		}
	}

}
