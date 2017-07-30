import java.util.ArrayList;

/* Hashing with chaining:
 * Each cell of the array contains a linked list the dedicated to storing keys with a same hash value
 * #keys=n, #cells(linked list)=m
 * Load factor: n/m
 * 
 */
public class ChainedHashTable<T> {
	private int size;
	private ArrayList<T> list;
	public ChainedHashTable(int size){
		this.size = size;
		this.list = new ArrayList<T>(size);
	}
	
	private int hash(T key){
		return 
	}
	
}
