package MP01;

import java.util.*;

// Borrower
public class Borrower {
    String borrowerName;
    HashMap<Integer, Long> borrowedBooks; // K - index, V - time
    Queue<Integer> borrowerReserve; // QueueNumber 
    HashMap<Integer, Book> bookIndex; // K - index, V - book

    // Constructor for Borrow.
    public Borrower(String name) {
        borrowerName = name;
        borrowedBooks = new HashMap<>();
        borrowerReserve = new LinkedList<>();	
        bookIndex = new HashMap<>();
    }

    // return String bookTitle.
    public String getName() {
        return borrowerName;
    }
    
    // Initialize book index with sorted titles.
    public void bookIndex(Book[] allBooks) {
        String[] titles = new String[allBooks.length];
        for (int i = 0; i < allBooks.length; i++) {
            titles[i] = allBooks[i].getTitle();
        }

        // Sort titles using quicksort.
        StringQuickSort sorter = new StringQuickSort();
        sorter.sort(titles);
        
        // Maap sorted titles to their indices.
        for (int i = 0; i < titles.length; i++) {
            for (int j = 0; j < allBooks.length; j++) {
                Book book = allBooks[j];
                if (book.getTitle() == (titles[i])) {
                    bookIndex.put(i, book);
                }
            }
        }
    }
    
    public HashMap<Integer, Long> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    // Return queue of borrower reserve.
    public Queue<Integer> getBorrowerReserve() {
        return borrowerReserve;
    }
 
    
    // Reservation and queue management for popular books.
    // Assume all books have one quantity each.
    /* CHECKOUT BOOK
     * If the book is available, add book and current time checkout.
     * Set availability to false as it is already checked out.
     * Set history for adjacency list.
     * Print remarks.
     * 
     * Else if not available, add reservation to the book.
     * Ask if the borrower wants to checkout.
     * Print remarks.
     */

    public void checkoutBook(int i) {
        Book book = bookIndex.get(i);
        if (book != null) {
            if (book.getAvailability()) {
                borrowedBooks.put(i, System.currentTimeMillis());
                book.setAvailability(false);
                book.addHistory(getName());
                System.out.println(getName() + " checked out the book, " + book.getTitle() + "\n");
            } else {
                borrowerReserve.add(i);
                System.out.println(book.getTitle() + " is currently checked out. " + getName() + " has been added to the reservation queue.\n");
            }
        } else {
            System.out.println("Invalid book index.\n");
        }
    }

    /* RETURN BOOK
     * If the book is borrowed, search for book be removed from the list.
     * Set availability to true as it is already returned.
     * Assure that the reservation is not empty to remove the first and have the book check out for the next in line.
     * Print remarks.
     * 
     * Else the book is not checked out, print remarks.
     */
    
    Scanner scan = new Scanner(System.in); // Get user input.
    
    public void returnBook(int i, ArrayList<Borrower> allBorrowers) {
        Book book = bookIndex.get(i); // Base from the key value of the book.
        if (book != null) {
            if (borrowedBooks.containsKey(i)) {
                book.setAvailability(true); // Availability
                long borrowBookTime = borrowedBooks.remove(i); // Value of checkout time in milliseconds.
                long returnBookTime = System.currentTimeMillis(); // Value of return time in milliseconds.
                long fine = calculatedFine(borrowBookTime, returnBookTime); // Calculate fine.
                System.out.println(getName() + " returned the book, " + book.getTitle() + " with a fine: Php " + fine + ".\n");

                // Check reservation queue for all borrowers
                for (int j = 0; j < allBorrowers.size(); j++) {
                	Borrower borrower = allBorrowers.get(j);
                    if (borrower.getBorrowerReserve().contains(i)) {
                        borrower.getBorrowerReserve().remove(i);
                        
                        // Question to checkout next reserved borrower.
                        // Print remarks.
                        System.out.println(borrower.getName() + " may now check out the book, do you wish to borrow?");
                        System.out.println("Enter Y: Yes, Enter N: No");
                        String input3 = scan.nextLine();
                        if (input3.equalsIgnoreCase("N")) {
                            System.out.println("Do proceed to check out if you ever change your mind.\n");
                        } else if (input3.equalsIgnoreCase("Y")) {
                            borrower.checkoutBook(i);
                        } else {
                            System.out.println("Invalid Input: proceeding back to home of the system.\n");
                        }
                    }
                }
            } else {
                System.out.println(getName() + " has no records of borrowing, " + book.getTitle() + ".\n");
            }
        } else {
            System.out.println("Invalid book index: return to system.\n");
        }
    }
    	
 
    // Fine calculation and management for overdue books.
    /* Fining Instructions: 
     * Duration of borrowing is 10 seconds
     * 1 Philippine peso per interval time, 5 seconds */
 
    long accumulatedFine = 1;
    long borrowDuration = 10 * 1000;
    long fineIntervalTime = 5 * 1000;
    
    // Calculation for fine. 
    public long calculatedFine(long borrowTime, long returnTime) {
    	long overdueTime = returnTime - borrowTime - borrowDuration;
    	if (overdueTime <= 0) {
    		return 0;
    	}
    	long fine = overdueTime / fineIntervalTime;
    	return fine*accumulatedFine;
    }


}
