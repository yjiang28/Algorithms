/* Traveling Salesman Problem
 * 
 */
public class TSP {
	
	// @returns: the shortest length of path 1st->1st node, which traverse each node exactly once.
	public static int shortestPath(int[][] dist){
		int size = dist.length;
		// m[i][j] stores the shortest distance of the path 1st -> j-th ->1st node,
		// which cross each node exactly once.
		int[][] m = new int[size+1][size+1];
		
		// num: number of nodes on the examined path
		// index: index of the node that goes directly to the 1st node at the end
		int result;
		for(int num=2; num<size+1; num++){
			for(int index=2; index<=num; index++){
				// if there is 2 nodes in the graph
				if(num == 2){
					m[2][2] = dist[1][2];
					return m[2][2];
				}
				else{
					int min = m[num-1][2]+dist[2][index], tmp;
					for(int i=3;i<index;index++){
						tmp = m[num-1][i]+dist[i][index];
						if(tmp<min) min = tmp;
					}
					m[num][index] = min;
				}
			}
			int min = m[num][2] + dist[2][num], tmp;
			for(int index=3; index<=num; index++){
				tmp = m[num][index]+dist[index][num+1];
				if(tmp<min) min = tmp;
			}
		}
		
	}
	
	public static void main(String[] args) {
		// dist[i][j] stores the distance between the i-th and the j-th nodes
		int[][] dist = new int[5][5];
		

	}

}
