public class majorityElement {
	
	
	public static void printArray(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println("");
	}
	
	
    public static int majorityElement(int[] nums) {
        // find the median of this array
        int size = nums.length;
        int median = find(size/2+1, nums, 0, size-1);
        // count the number of elements equivalent to the median
        int count=0;
        for(int i=0;i<size;i++){
            if(nums[i]==median) count++;
        }
        if(count>size/2) return median;
        else{
            System.out.println("Such a majority element does not exist.");
            return -1;
        }
    }
    
    // find the k-th smallest element in array nums in the range [head, tail]
    public static int find(int k, int[] nums, int head, int tail){
    	System.out.println("k="+k+" head="+head+" tail="+tail);
    	if(k<=0 || k>nums.length){
    		System.out.println("Invalid k value");
    		return -1;
    	}
    	if(head<0 || tail>=nums.length){
    		System.out.println("Invalid head or/and tail value(s)");
    		return -1;
    	}
    	int i, tmp;
        int size = tail-head+1;
        if(size<=5){
            // use brute force by bubble sort
            for(i=size;i>0;i--){
                for(int j=head+1;j<=tail;j++){
                    if(nums[j]<nums[j-1]){
                        tmp = nums[j];
                        nums[j] = nums[j-1];
                        nums[j-1] = tmp;
                    }
                }
            }
            printArray(nums);
            return nums[head+k-1];
        }
        // partition nums into size/5 groups of 5 elements
        // and find the median of each group
        int[] medians;
        if(size%5 == 0){
        	medians = new int[size/5];
            for(i=0;i<size/5;i++){
                medians[i] = find(3, nums, head+5*i, head+5*i+4);
            }
        }
        else{
        	medians = new int[size/5+1];
            for(i=0;i<size/5;i++){
                medians[i] = find(3, nums, head+5*i, head+5*i+4);
            } 
            medians[size/5] = find((size%5)/2+1, nums, head+5*i, head+size-1);
        }
        printArray(medians);
        // find the median of medians[]
        int m = find(medians.length/2+1, medians, 0, medians.length-1);
        System.out.println("Median of medians="+m);
        // partition nums into two parts, with the left containing all elements smaller than m
        // switch m with the last element in nums
        for(i=head;i<=tail;i++){
            if(nums[i] == m) break;
        }
        System.out.println("Index of median="+i);
        printArray(nums);
        tmp = nums[tail];
        nums[tail] = m;
        nums[i] = tmp;
        printArray(nums);
        // start partitioning
        int l=head, r=tail-1;
        while(l<r){
            if(nums[l]>m){
                tmp = nums[r];
                nums[r] = nums[l];
                nums[l] = tmp;
                System.out.print("l="+l+" ");
                printArray(nums);
                r--;
            }
            else l++;
        }
        // now elements in the range [0, l-1] <= m, [r, size-2] > m 
        System.out.println("Recurse");
        printArray(nums);
        int sizeL, tailL, headR;
        if(nums[l]<=m) tailL=l;
        else tailL=l-1;
        headR=l+1;
        sizeL=tailL-head+1;
        if(k<=sizeL)return find(k, nums, head, tailL);
        else if(k==sizeL+1) return m;
        else return find(k-sizeL, nums, headR, tail-1);
    }
    
    public static void main(String args[]){
    	int[] nums = {4,5,4,5,4,5};
    	System.out.println("length="+nums.length);
    	int m = majorityElement(nums);
    	System.out.println("m="+m);
    }
}