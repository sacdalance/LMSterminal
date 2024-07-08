package MP01;

import java.util.*;

/* Agarwal, K. (2020) How to implement a graph in Java using arraylist, YouTube. 
 * Available at: https://www.youtube.com/watch?v=UhFfdBdHCJM (Accessed: 14 June 2024). 
 */
	
// Prints borrowing history of each book.
public class AdjacencyList {
	
	public void createHistory(Book[] array) {
	// Loop throughout allBooks. 
	for (int i = 0; i < array.length; i++) {
		Book book = array[i];
        List<String> history = book.getHistory();
        // Check book if it has history.
        if (!history.isEmpty()) {
            System.out.print(book.getTitle() + ": -> ");
            // Print all history per book checkout.
            for (int j = 0; j < history.size(); j++) {
                if (j > 0) {
                    System.out.print(" -> ");
                }
                System.out.print(history.get(j));
            }
            System.out.println();
        }
        else {
        	// Print "NONE" if the book has no history of checkout.	
        	System.out.println(book.getTitle() + ": NONE");
        }
    }
    System.out.println();
	}
}

/* Agarwal, K. (2020) How to implement a graph in Java using arraylist, YouTube. 
 * Available at: https://www.youtube.com/watch?v=UhFfdBdHCJM (Accessed: 14 June 2024). 
 */

