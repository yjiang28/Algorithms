
public class Searching {
	
	/* Linear search for x in unordered array a
	 * @param a: the array to search in
	 * @param x: the integer to search for
	 * @returns a boolean indicating if x is in a[]
	 */
	public boolean isIn(int[] a, int x){
		int i;
		for(i=0;i<a.length;i++){
			//@loopInvariant 0<=i<=a.length
			if(a[i]==x) return true;
		}
		return false;
	}
	
	/* Search for x in a sorted array in ascending order a[]
	 * @param a: the array to search in
	 * @param x: the integer to search for
	 * @returns -1 if x is not in a, the index of x otherwise 
	 */
	public int binarySearch(int[] a, int x){
		//@requires isSorted(a)
		//@ensures \result==-1&&!isIn(a,x) || a[\result]==x
		int lower=0, upper=a.length-1, mid;
		while(lower<upper){
			//@loopInvariant 0<=head<tail<=a.length-1
			//@loopInvariant lower==0 || a[lower-1]<x
			//@loopInvariant upper==a.length-1 || a[upper+1]>x
			mid = lower+(upper-lower)/2;	// prevent overflow when lower+upper>2^31-1
			if(x<a[mid]) upper=mid;
			else if(x>a[mid]) lower=mid;
			else return a[mid];
		}
		//@assert lower==upper && !isIn(a,x)
		return -1;		
	}
	
	/* Linear check if array a[] is sorted
	 * @param a: the array to check
	 * @returns a boolean indicating if a[] is sorted
	 */
	public boolean isSorted(int[] a){ 
		int i;
		for(i=0;i<a.length-1;i++){
			//@loopInvariant a.length==1 || 0<=i<=a.length-1
			if(a[i]<=a[i+1]) return false;
		}
		return true;
	}
	
}
