package MP01;

import java.util.*;

// Book checkout and return.

public class Book {
	String bookTitle;
	String bookAuthor;
	String bookPublicationDate;
	boolean bookAvailability;
	List<String> borrowHistory;

	// Constructor for Book
	public Book(String title, String author, String date) {
		bookTitle = title; // To be sorted (ascending order).
		bookAuthor = author;
		bookPublicationDate = date;
		bookAvailability = true; // Initially true
		borrowHistory = new ArrayList<>();
	}

	// return String bookTitle.
	public String getTitle() {
		return bookTitle;
	}

	// return String bookAuthor.
	public String getAuthor() {
		return bookAuthor;
	}

	// return String bookPublicationDate.
	public String getDate() {
		return bookPublicationDate;
	}
	
    @Override
    public String toString() {
        return bookTitle;
    }
	
	// return boolean bookAvailability.
	public boolean getAvailability() {
		return bookAvailability;
	}

	// Assuming only one book is available for reservation function.
	public void setAvailability(boolean availability) {
		bookAvailability = availability;
	}
	
	// return List getHistory.
	public List<String> getHistory() {
		return borrowHistory;
	}
	
	// Add name of the borrower, history of borrow per book.
	public void addHistory(String borrower) {
		borrowHistory.add(borrower);
	}
}
