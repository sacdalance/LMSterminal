package MP01;

/* Sruthy (2024) Binary search tree in java - implementation & code examples, Software Testing Help - FREE IT Courses and Business Software/Service Reviews.
 * Available at: https://www.softwaretestinghelp.com/binary-search-tree-in-java/ (Accessed: 14 June 2024). 
 */
// Node class
public class TreeNode {
    Book value;
    TreeNode left, right;
    
    // Constructor
    public TreeNode(Book book) {
        value = book;
        left = right = null;
    }
    
    
    // insert a node in BST
    public void insert(Book addbook) {
        if (addbook.getTitle().compareTo(value.getTitle()) < 0) {
            if (left == null) {
                left = new TreeNode(addbook);
            } 
            
            else {
                left.insert(addbook);
            }
        } 
        	
        else {
        	if (right == null) {
        		right = new TreeNode(addbook);
        	} 
        	else {
        		right.insert(addbook);
        	}
        }
    }

    
 // Search for a book by title in BST
    public TreeNode search(String title) {
    	// Remove case sensitivity.
        if (value.getTitle().equalsIgnoreCase(title)) {
            return this;
        }
        if (title.compareToIgnoreCase(value.getTitle()) < 0) {
            if (left != null) {
                return left.search(title);
            }
        } 
        else {
            if (right != null) {
                return right.search(title);
            }
        }
        return null;
        }
/* Sruthy (2024) Binary search tree in java - implementation & code examples, Software Testing Help - FREE IT Courses and Business Software/Service Reviews.
* Available at: https://www.softwaretestinghelp.com/binary-search-tree-in-java/ (Accessed: 14 June 2024). 
*/

    
    /* Novikov, V. and Davies, T. (2014) How to print binary tree diagram in Java?, Stack Overflow. 
     * Available at: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java (Accessed: 14 June 2024). 
     */
    	
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value.toString()).append("\n");
        if (left != null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }
   
    /* Novikov, V. and Davies, T. (2014) How to print binary tree diagram in Java?, Stack Overflow. 
     * Available at: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java (Accessed: 14 June 2024). 
     */
}

