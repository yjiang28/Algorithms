
public class practices {
	
	/* Find the two maximal values in an unsorted array
	 * @param a: an unsorted int array
	 */
	public static void twoMax(int a[]){
		if(a==null || a.length==0) return;
		int max1=a[0], max2=a[0];	
		for(int i=0;i<a.length;i++){
			//@loopInvariant max1>=max2
			if(a[i]>max1){
				max2=max1;
				max1=a[i];
			}
			else if(a[i]>max2) max2=a[i];
		}
		System.out.println("Maxima:"+max1+" "+max2);
	}
	
	public static void main(String[] args){
		int a[] = {39,40,23,47,28,34,9,2};
		twoMax(a);
	}
}
