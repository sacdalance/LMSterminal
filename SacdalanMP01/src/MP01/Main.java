package MP01;

import java.util.*;

public class Main {
	public static void main(String[] args) {
	
		// Array for all books.
		Book[] allBooks = {
				new Book("Noli Me Tangere", "Jose Rizal", "1887"),
				new Book("The Woman Who Had Two Navels", "Nick Joaquin", "1961"),
				new Book("Po-on", "Francisco Sionil Jose", "1983"),
				new Book("Banaag at Sikat", "Lope K. Santos", "1906"),
				new Book("State of War", "Ninotchka Rosca", "1988"),
				new Book("Mga Ibong Mandaragit", "Amado V. Hernandez", "1969"),
				new Book("Ang Tundo Man Ay Langit Din", "Andres Cristobal Cruz", "1986"),
				new Book("Sa Mga Kuko ng Liwanag", "Edgardo M. Reyes", "1986"),
				new Book("America Is in the Heart", "Carlos Bulosan", "1946"),
				new Book("My Sad Republic", "Eric Gamalinda", "2000")
			};
		
		// ArrayList for all borrowers. 
        ArrayList<Borrower> allBorrowers = new ArrayList<>();
        allBorrowers.add(new Borrower("Lance"));
        allBorrowers.add(new Borrower("Gabriel"));
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("		    Library Management System");
		
		Scanner scan = new Scanner(System.in); // Get user input.
	    boolean system = true; // Initially true. 
	    
	    // Continuous terminal I/O.
		while(system) {
			// Printed options (Instructions).
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Enter 0, print to see all newly added books.");
			System.out.println("Enter 1, TO RETURN OR CHECKOUT: "
					+ "			\n	 print all sorted books by title to continue in the system.");
			System.out.println("Enter 2, print borrow history.");
			System.out.println("Enter 3, print a binary tree.");
			System.out.println("Enter 4, search for books using binary tree nodes.");
			System.out.println("Enter 5, exit system.");
			System.out.println("-------------------------------------------------------------------");
			
			int input = scan.nextInt(); // Get user options, by integer (corresponding to index).
        
			// Print newly added books.
			if (input == 0) {
				System.out.println("\nNewly added books!!!\n");				
				for (int i = 0; i < allBooks.length; i++) { 
					Book book = allBooks[i];
					System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getDate());
				}
	            System.out.println("");
			}
			
			// Return or Checkout SORTED TITLES
            else if (input == 1) {
                System.out.println("Select registered names in library:");
                for (int i = 0; i < allBorrowers.size(); i++) {
                    System.out.println(i + ": " + allBorrowers.get(i).getName());
                }

                int borrowerIndex = scan.nextInt();

                if (borrowerIndex < 0 || borrowerIndex >= allBorrowers.size()) {
                    System.out.println("Invalid borrower index.");
                    continue;
                }

                // Sort the titles
                // Reference needed.
                String[] titles = new String[allBooks.length];
                for (int i = 0; i < allBooks.length; i++) {
                    titles[i] = allBooks[i].getTitle();
                }
                // Reference needed.
                
                if (borrowerIndex >= 0 && borrowerIndex < allBorrowers.size()) {
                	
                	// Get value of hashmap from borrower the corresponding Integer for selection.
                    Borrower borrower = allBorrowers.get(borrowerIndex); 
                    borrower.bookIndex(allBooks);

                    System.out.println("\n\nSorted titles:"); // Show selection.
                    for (int i = 0; i < borrower.bookIndex.size(); i++) {
                        System.out.println(i + ": " + borrower.bookIndex.get(i).getTitle());
                    }
                    System.out.println("");
                    
                    // Show instructions for transaction.
                    System.out.println("-------------------------");
                    System.out.println("Enter 0, checkout a book.");
                    System.out.println("Enter 1, return a book.");
                    System.out.println("-------------------------");
                    
                    // Scan integer input.
                    int input2 = scan.nextInt();

                    // Checkout a book.
                    if (input2 == 0) {
                        System.out.println("\nEnter the index of the book to checkout:");
                        int bookIndex = scan.nextInt();
                        borrower.checkoutBook(bookIndex);
                    }

                    // Return a book.
                    else if (input2 == 1) {
                        System.out.println("Enter the index of the book to return:");
                        int bookIndex = scan.nextInt();
                        borrower.returnBook(bookIndex, allBorrowers);
                    }
                    
                    // Else, invalid input.
                    else {
                    	System.out.println("Invalid input: return to system.\n");
                    }
                }
            }
			
			// Print borrow history. 
            else if (input == 2) {
            	System.out.println("\nBorrow History\n");
            	AdjacencyList history = new AdjacencyList();
            	history.createHistory(allBooks);
            }
			
			// Print BinarySearchTree representation.
            else if (input == 3) {
                TreeNode root = new TreeNode(allBooks[0]);
                for (int i = 1; i < allBooks.length; i++) {
                    root.insert(allBooks[i]);
                }
                System.out.println(root.toString());
            }

			// Search for books
            else if (input == 4) {
                TreeNode root = new TreeNode(allBooks[0]);
                for (int i = 1; i < allBooks.length; i++) {
                    root.insert(allBooks[i]);
                }
                
                scan.nextLine(); // consumes newLine, not like nextInt. (accept not equal)
                
                System.out.println("Search title:");

                String title = scan.nextLine();
                
                // If not null, book found, else, book not found.
                TreeNode result = root.search(title);
                	if (result != null) {
                		System.out.println("Book found: " + result.value + "\n");
                	} 
                	else {
                		System.out.println("Book not found.\n");
                        continue;
                	}
            }

			// Exit terminal.
			else if (input == 5) {
				System.out.println("Exit terminal.\n");
				system = false;
			}
			
			// Close terminal if entered index not included. 
			else {
				System.out.println("Invalid input: try again.\n");
			}
		}
	}
}

