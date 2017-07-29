import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Random;

public class BST<T extends Comparable<T>> extends Tree<T>{
	
	public static class BSTNode<T extends Comparable<T>> extends Node<T>{
		BSTNode<T> l, r, parent;
		public BSTNode(T value){
			super(value);
			l=null;
			r=null;
		}
	}
	
	private LinkedList<T> inorder;
	
	BSTNode<T> root;
	
	public BST(BSTNode<T> root){
		super(root);
		inorder = new LinkedList<T>();
	}
	
	public BST(){
		super();
		inorder = new LinkedList<T>();
	}
	
	/* Get the size of the BST iteratively by deploying queue data structure
	 * @returns the number of node in the BST rooted at node root
	 */
	public int getSizeIter(){
		if(root==null) return 0;
		Queue<BSTNode<T>> q = new LinkedList<BSTNode<T>>();
		q.add(root);
		int count = 0;
		while(!q.isEmpty()){
			BSTNode<T> node = q.poll();
			count++;
			if(node.l!=null) q.add(node.l);
			if(node.r!=null) q.add(node.r);
		}
		return count;
	}
	
	/* Get the size of the BST 
	 * @param count: initial value must be 0
	 */
	public int getSizeRec(BSTNode<T> root, int count){
		if(root==null) return count;
		count++;
		if(root.l!=null) count += getSizeRec(root.l, 0);
		if(root.r!=null) count += getSizeRec(root.r, 0);
		return count;
	}
	
	/* Postorder traversal iteratively using 1 stack
	 * @override
	 */
	public void postorderIter1(){
		//@requires postorder.isEmpty()
		//@requires this!=null && this.root!=null
		Stack<BSTNode<T>> s = new Stack<BSTNode<T>>();
		BSTNode<T> node = this.root; 
		s.push(node);
		while(!s.isEmpty()){
			if(node==null){
				node=s.pop();
				postorder.add(node.value);
				if(node.parent.r==s.peek()) node=node.parent.r;
			}
			else{
				if(node.r!=null) s.push(node.r);       
				node = node.l;
				if(node!=null) s.push(node);	
			}
		}
	}
	
	public void inorder(BSTNode<T> root){		
		//@requires inorder.isEmpty()
		//@requires root!=null && root.value!=null
		if(root==null || root.value==null) return;
		//@loopInvariant 0<=i<root.children.size()
		inorder(root.l);
		this.inorder.add(root.value);
		inorder(root.r);
		return;
	}
	
	/* Check if this BST preserve order invariants by in order traversal
	 * @returns if this BST is ordered
	 */
	public boolean isOrderedInOr(){
		inorder(this.root);
		int i;
		for(i=0;i<inorder.size()-1;i++){
			if(inorder.get(i).compareTo(inorder.get(i+1)) > 0) return false;
		}
		return true;
	}

	/* Check if this BST preserve order invariants by setting upper and lower bounds
	 * @returns if this BST is ordered
	 */
	public boolean isOrderedBound(BSTNode<T> root, T lower, T upper){
		//@requires root node has no lower or upper bound
		if(root == null) return true;
		if(lower!=null && root.value.compareTo(lower)<0) return false;
		if(upper!=null &&root.value.compareTo(upper)>0) return false;
		return isOrderedBound(root.l, lower, root.value) && 
					isOrderedBound(root.r, root.value, upper);
	}
	
	/* Search for key in BST using iteration
	 * @param key: the key to search for
	 * @returns a boolean indicating if key is in this BST
	 */
	public boolean findKeyIter(BST<T> tree, T key){
		//@requires tree is ordered
		BSTNode<T> root = tree.root;
		while(root!=null){
			if(key == root) return true;
			if(root.value.compareTo(key)<0) root = root.l;
			else root = root.r;
		}
		//@assert root==null
		return false;
	}
	
	/* Search for key in BST using recursion
	 * @param key: the key to search for
	 * @returns the node containing the key; null if it doesn't exist
	 */
	public BSTNode<T> findKeyRec(BSTNode<T> root, T key){
		//@requires the tree rooted at root is ordered
		if(root == null) return null;
		if(root.value == key) return root;
		else if(root.value.compareTo(key)<0) return findKeyRec(root.l, key);
		else return findKeyRec(root.r, key);
	}
	
	/* Insert a node into this BST with the order invariants hold 
	 * @param root: the root node of this BST
	 * @param node: the node to be inserted
	 * @returns the root of this BST
	 */
	public BSTNode<T> nodeInsertion(BSTNode<T> root, BSTNode<T> node){
		//@requires findKey(root, node.value)==null
		//@requires node!=null && node.value!=null
		//@ensures findKey(this.root, node.value)==node
		//@ensures this.isOrdered()
		if(this.root == null){
			this.root = node;
		}
		else if(node.value.compareTo(root.value) < 0){
			if(root.l == null){
				root.l = node;
				node.parent = root;
			}
			else nodeInsertion(root.l, node);
		}
		else{
			if(root.r == null){
				root.r = node;
				node.parent = root;
			}
			else nodeInsertion(root.r, node);
		}
		return this.root;
	}
	
	/* A right rotation put the left child to be the root 
	 * @returns the new root
	 */
	public BSTNode<T> rightRotate(BSTNode<T> root){
		//@requires root!=null && root.l!=null
		//@ensures this.isOrdered(newRoot)
		if(root==null || root.l == null) return root;
		BSTNode<T> newRoot = root.l;
		if(newRoot.r!=null){
			root.l = newRoot.r;
			newRoot.r.parent = root;
		}
		newRoot.r = root;
		root.parent = newRoot;
		return newRoot;
	}
	
	/* A left rotation put the right child to be the root
	 * @returns the new root
	 */
	public BSTNode<T> leftRotate(BSTNode<T> root){
		//@requires root!=null && root.r!=null
		//@ensures this.isOrdered(newRoot)
		if(root==null || root.r == null) return root;
		BSTNode<T> newRoot = root.r;
		if(newRoot.l != null){
			root.r = newRoot.l;
			newRoot.l.parent = root;
		}
		newRoot.l = root;
		root.parent = newRoot;
		return newRoot;
	}

	/* Insert a node to be the root of this BST 
	 * @param root: the root node of this BST
	 * @param node: the node to be inserted
	 * @returns the new root of this BST 
	 */
	public BSTNode<T> rootInsertion(BSTNode<T> root, BSTNode<T> node){
		//@requires node!=null
		//@requires this.isOrdered(root)
		//@requires this.findKey(node.value)==null
		//@ensures this.isOrdered(\result)
		//@ensures this.findKey(node.value)==node
		if(root == null){
			root = node;
			node.parent = root;
		}
		else if(node.value.compareTo(root.value) < 0){
			root.l = rootInsertion(root.l, node);
			root = rightRotate(root.l);
		}
		else{
			root.r = rootInsertion(root.r, node);
			root = leftRotate(root.r);
		}
//		this.root = root;
		return root;
	}
	
	/* Insert a node such that it has 1/(n+1) possibility of becoming the root of this n-node BST
	 * @param root: the root node of this BST
	 * @param node: the node to be inserted
	 * @returns the new root of this BST  
	 */
	public BSTNode<T> randomInsertion(BSTNode<T> root, BSTNode<T> node){
		//@requires node!=null && node.value!=null
		//@ensures isOrdered(this.root) && isBalanced(this.root)
		if(root == null){
			this.root = node;
			node.parent = root;
			return this.root;
		}
		int size = this.getSizeIter();
		Random rand = new Random();
		if(rand.nextInt(size+1) == 0) rootInsertion(root, node);
		else nodeInsertion(root, node);
		return this.root;
	}
	
	public static void printListInt(LinkedList<Integer> l){
		for(int elem: l) System.out.print(elem+" ");
	}
	
	public static void main(String args[]){
		BST<Integer> tree = new BST<Integer>();
		
		for(int i=0;i<10;i++){
			BSTNode<Integer> node = new BSTNode<Integer>(i);
			tree.randomInsertion(tree.root, node);
		}
		tree.inorder.clear();
		tree.inorder(tree.root);
		printListInt(tree.inorder);
	}
}
