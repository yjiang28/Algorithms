import java.util.Random;

public class Sorting {
	
	public static void printArray(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println("");
	}
	
	/* In place sorting 
	 * Time complexity: O(n^2)
	 * Iterate through the array. Compare an element with each of the nodes in front of it. 
	 *  
	 * @param a: the array of int to be sorted.
	 * @returns a sorted array containing all elements in a.
	 */
	public static int[] insertionSort(int[] a){
		//@ensures /result is an sorted array from the smallest to the largest elements in a.
		int temp, i,j;
		for(i=1;i<a.length;i++){
			//@loopInvariant 1<=i<=a.length
			//@ensures a[j]<=a[i] for all j<i
			for(j=i;j>=0;j--){
				//@loopInvariant 0<=j<=i
				//@ensures a[j-1]<=a[j]
				if(a[j]>a[j-1]) break;
				else{
					//swap
					temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
				}
			}
			
		}		
		//@assert i==a.length
		return a;
	}
	
	/* In place sorting 
	 * Time complexity: O(n^2)
	 * 
	 * @param a: the array of int to be sorted.
	 * @returns a sorted array containing all elements in a. 
	 */
	public static int[] bubbleSort(int[] a){
		//@ensures /result is a sorted array containing all elements in a.
		int i, j, tmp;
		for(i=a.length-1;i>=0;i--){
			//@loopInvariant 0<=i<=a.length
			//@loopInvariant elements from the i-th to the last one in a is sorted
			for(j=0;j<i;j++){
				//@loopInvariant 0<=j<i
				//@loopInvariant the largest number in the unsorted part of array is bubbled to the i-th cell in a.
				if(a[j]>a[j+1]){
					//swap
					tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
		}
		return a;
	}
	
	/* @param a: an array where to look for min
	 * @param lft & r: the head and tail (inclusive) of the array segment to look min for
	 * @returns the index of the smallest element in the specified array segement 
	 */
	public static int findMinIndex(int[] a, int lft, int r){
		//@requires l<=r
		//@ensures /result is the smallest integer in the lth to the rth element in a
		int minIndex = lft, i;
		for(i=lft+1;i<=r;i++){
			//loopInvariant lft<i<=r
			//loopInvariant a[minIndex]<=a[k] for lft<k<=i
			if(a[i]<a[minIndex]) minIndex = i;
		}
		//@assert i>r
		return minIndex;
	}
	
	/* In place sorting algorithm that takes O(n^2) time and no extra space.
	 * @param a: the array of int to be sorted.
	 * @returns a sorted array containing all elements in a. 
	 */
	public static int[] selectionSort(int[] a){
		//@ensures /result is a sorted array containing all elements in a.
		//find the smallest element in the unsorted segment of a.
		int i, minIndex, temp;
		for(i=0;i<a.length;i++){
			//@loopInvariant 0<i<a.length
			//@loopInvariant a[j]<=a[i] for 0<=j<=i
			minIndex = findMinIndex(a, i, a.length-1);
			//swap
			temp = a[i];
			a[i] = a[minIndex];
			a[minIndex] = temp;
		}
		return a;
	}
	
	/* In place sorting algorithm that takes O(n^2) in the worst case but O(nlogn) on average
	 * 
	 */
	public static void quickSort(int[] a, int head, int tail){
		//@requires 0<=head<=tail<=a.length
		//@ensures /result is a sorted array containing all elements in a
		if(tail-head <= 1) return; 
		//get the pivot index randomly
		Random rand = new Random();
		int pivot = rand.nextInt(tail-head)+head;
		int value=a[pivot];
		//swap pivot value with the last integer in a
		int tmp = a[tail];
		a[tail] = value;
		a[pivot] = tmp;
		//polarize integers <= pivot to the left and those > pivot to the right
		int lft=head, r=tail-1;
		while(lft<=r){
			//@loopInvariant lft<=r
			//@loopInvariant a[i]<=value for 0<=i<=lft && a[i]>value for r<=i<a.length-2
			if(a[lft]>value){
				//swap a[lft] with a[r]
				tmp = a[lft];
				a[lft] = a[r];
				a[r] = tmp;
				r--;
			}
			else lft++;
		}
		//@assert a[lft]>value
		//swap pivot value back
		tmp = a[lft];
		a[lft] = a[tail];
		a[tail] = tmp;
		quickSort(a, head, lft-1);
		quickSort(a, lft, tail);
		
		return;
	}
	
	
	
//	public static void main(String[] args) {
//		int[] a = {14,23,70,9,43,5,8,7,3,29};
////		int[] bubbleSort = bubbleSort(a); 
////		int[] insertionSort = insertionSort(a);
////		int[] selectionSort = selectionSort(a);
//		quickSort(a, 0, 9);
//		printArray(a);
//		
//
//	}

}
