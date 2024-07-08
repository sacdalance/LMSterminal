package MP01;

/* Smith, J. (2016) Using quicksort on a string array, Stack Overflow. 
 * Available at: https://stackoverflow.com/questions/29294982/using-quicksort-on-a-string-array (Accessed: 14 June 2024). 
 */

// Quicksort (Median-of-Three) - String
public class StringQuickSort {
	String titles[];
	int length;

	void sort(String array[]) {
		if (array == null || array.length == 0) {
		return;
	}
		
	this.titles = array;
	this.length = array.length;
	quickSort(0, length - 1);
 }

	void quickSort(int low, int high) {
		int i = low;
		int j = high;
		String pivot = this.titles[low + (high - low) / 2]; // Median-of-three
	
		System.out.println(" ");
		printArray(titles); // Print for iteration. (required)

		while (i <= j) {
			while (this.titles[i].compareToIgnoreCase(pivot) < 0) {
				i++;
			}

			while (this.titles[j].compareToIgnoreCase(pivot) > 0) {
				j--;
			}

			if (i <= j) {
				exchangeNames(i, j);
				i++;
				j--;
			}
		}
     
		// Call Quicksort recursively.
		if (low < j) {
			quickSort(low, j);
		}
		if (i < high) {
			quickSort(i, high);
		}
	}

	// Print array function.
	void printArray(String array[]) { 
		for (int i = 0; i < array.length; i++) {
			if (array[i].length() > 0) {
				// Print only the first two index for simplicity.
				System.out.print(array[i].charAt(0) + "" + array[i].charAt(1) + " "); 
			}
			else {
				System.out.println(" ");
			}
		}
	}
	
	// Swapping function.
	void exchangeNames(int i, int j) { 
		String temp = this.titles[i];
		this.titles[i] = this.titles[j];
		this.titles[j] = temp;
	}
}

/* Smith, J. (2016) Using quicksort on a string array, Stack Overflow. 
 * Available at: https://stackoverflow.com/questions/29294982/using-quicksort-on-a-string-array (Accessed: 14 June 2024). 
 */

