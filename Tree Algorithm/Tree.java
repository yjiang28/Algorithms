import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T extends Comparable<T>> {
	
	public static class Node<T extends Comparable<T>>{
		T value;
		LinkedList<Node<T>> children;
		Node<T> parent;
		
		public Node(T value){
			if(value instanceof Comparable){
				this.value = value;
				this.children = new LinkedList<Node<T>>();
				this.parent = null;
			}
			else System.out.println("Node's value must be comparable to build an ordered tree.");
		}
	}
	
	Node<T> root;
	int height, numNodes, numEdges;
	LinkedList<T> preorder, postorder;
	
	public Tree(Node<T> root){
		this.root = root;
		// height: max # edges between the root and a leaf 
		this.height = 0;
		this.numNodes = 1;
		this.numEdges = 0;
		this.preorder = new LinkedList<T>();
		this.postorder = new LinkedList<T>();
	}
	
	public Tree(){
		this.root = null;
		// height: max # edges between the root and a leaf 
		this.height = 0;
		this.numNodes = 0;
		this.numEdges = 0;
		this.preorder = new LinkedList<T>();
		this.postorder = new LinkedList<T>();
	}
	
	public void preorderIter(){
		if(root==null || root.value==null) return;
		Queue<Node<T>> q = new LinkedList<Node<T>>();
		q.add(root);
		preorder.add(root.value);
		while(!q.isEmpty()){
			Node<T> node = q.poll();
			preorder.add(node.value);
			for(Node<T> child: node.children){
				q.add(child);
			}
		}
	}
	
	
	public void preorderRec(Node<T> root){
		//@requires preorder.isEmpty()
		//@requires root!=null && root.value!=null
		if(root==null || root.value==null) return;
		System.out.println(root.value);
		preorder.add(root.value);
		for(int i=0;i<root.children.size();i++){
			//@loopInvariant 0<=i<root.children.size()
			preorderRec(root.children.get(i));
		}
		return;
	}
	
	/* Postorder traversal iteratively using 1 stack
	 */
//	public void postorderIter1()
	
	/* Postorder traversal iteratively using 2 stacks
	 */
	public void postorderIter2(){
		if(root==null || root.value==null) {System.out.println("Empty root");return;}
		Stack<Node<T>> s1 = new Stack<Node<T>>(),
					   s2 = new Stack<Node<T>>();
		s1.add(root); 
		while(!s1.isEmpty()){
			Node<T> node = s1.pop();
			s2.add(node);
			for(Node<T> child: node.children){
				s1.add(child);
			}
		}
		while(!s2.isEmpty()){
			this.postorder.add(s2.pop().value);
		}
	}
	
	/* Postorder traversal using recursion
	 */
	public void postorderRec(Node<T> root){		
		//@requires postorder.isEmpty()
		//@requires root!=null && root.value!=null
		if(root==null || root.value==null) return;
		for(int i=0;i<root.children.size();i++){
			//@loopInvariant 0<=i<root.children.size()
			Node<T >elem = root.children.get(i);
			postorderRec(elem);
		}
		postorder.add(root.value);
		return;
	}
	
	/* Build an ordered rooted tree from the preorder traversal and degree of each node
	 * @param root: the root node of the tree to be built
	 * @param value: the preorder traversal of the tree
	 * @param degree: the degree of nodes in the according position in array value
	 */
	public int buildTree(Node<T> root, T[] value, int[] degree, int start){
		if(start==value.length-1) return value.length-1;
		int i=0, next=start+1;
		while(i<degree[start]){
			Node<T> child = new Node<T>(value[next]);
			root.children.add(child);
			child.parent = root;
			System.out.println(child.value+" is child of "+root.value);
			next = buildTree(child, value, degree, next);
			i++;
		}
		return next;
	}
	
	public static void printList(LinkedList<String> l){
		for(String elem: l) System.out.print(elem+" ");
	}
	
	public static void main(String args[]){
		Node<String> root = new Node<String>("A");
		Tree<String> tree = new Tree<String>(root);
		String[] preorder = {"A","B","C","D","E","F"};
		int[] degree = {3,0,2,0,0,0};
		tree.buildTree(root, preorder, degree, 0);
		
		tree.postorderRec(root);
		printList(tree.postorder); System.out.println("");
		tree.postorder.clear();
		
		tree.postorderIter2();
		printList(tree.postorder); System.out.println("");
		tree.postorder.clear();
		
//		tree.postorderIter1(); 
//		printList(tree.postorder); System.out.println("");
	}
}
