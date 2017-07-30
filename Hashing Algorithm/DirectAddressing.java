import java.util.LinkedList;
import java.util.Stack;

public class DirectAddressing {
	/* K=Key space && k=|K|
	 * M=Map space && m=|M|
	 * Problem:
	 * If k << m, then a very large array is needed and most of it will be unused.
	 * Initializing such array by making each cell null may cost O(m).
	 * Method 1: 
	 * - Find the max and min in M and consider everything between them to be in the array
	 * - Problem: possible waste of space resulting in O(m) to clean the garbage
	 * Method 2:
	 * - Avoid initialization and use back-pointers instead
	 * - I deploy this solution below.
	 */
	int k, m, last;
	Stack<Integer> s = new Stack<Integer>();	// contains the real key value
	// t1[i] = the value whose hash result is i
	// t2[i] = the index in the stack of the value whose hash result is i
	int[] t1, t2;	
	
	public DirectAddressing(int k, int m){
		this.k = k;
		this.m = m;
		last = 0;
		t1 = new int[m];
		t2 = new int[m];
	}
	
	public int hash(int key){
		return 0;
	}
	
	public void insert(int x){
		last++;
		int key = hash(x);
		t1[key] = x;
		t2[key] = last;
		try{
			s.set(last, key);
		}catch(ArrayIndexOutOfBoundsException e){
			s.add(key);
		}
	}
	
	public void delete(int x){
		int key = hash(x),
			index = t2[key];
		t1[key] = (Integer) null;
		t2[key] = last;
		s.set(t2[key], key);
		last--;
	}
	
	public int search(int x){
		int key = hash(x);
		if(key<=last && key == s.get(t2[key])){
			return t1[key]; 
		}
		else return (Integer) null;
	}
	
}
