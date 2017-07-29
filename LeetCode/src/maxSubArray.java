
public class maxSubArray {

	public static int maxSubArray(int[] a, int head, int tail, Integer sumL, Integer sumPrefix, Integer sumR, Integer sumSuffix){
		if(head<0 || tail>a.length){
			System.out.println("Invalid head or/and tail input");
		}
		if(head==tail){
			sumL=a[head];
			sumPrefix=a[head];
			sumR=a[head];
			sumSuffix=a[head];
		}
		int mid = (head+tail)/2;
		maxSubArray(a, head, mid, sumL, sumPrefix, sumR, sumSuffix);
		maxSubArray(a, mid+1, tail, sumL, sumPrefix, sumR, sumSuffix);
		int result = Math.max(sumR, Math.max(sumL, sumPrefix+sumSuffix));
		return result;
	}

	
	
	public static void main(String[] args) {
		int[] a = {-2,1,-3,4,-1,2,1,-5,4};
		int result = maxSubArray(a, 0, a.length-1, null, null, null, null);
	}

}
