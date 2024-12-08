
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class AListTraversal {
    private boolean[] visited; // Tracker for both traversal processes
    private Queue<Integer> queue; // tracks when the cluster is completed using bfs
    private Stack<Integer> stack;// tracks when the clust is completed using dfs traversal
    private ArrayList<LinkedList<Integer>> traversalResult; // stores traversal result
    
    // default constructor
    // *************************************************************************
    public AListTraversal(){}
    
    // constructor for bfs traversal
    // *************************************************************************
    public AListTraversal(ArrayList<LinkedList<Integer>> aList){
        this.visited = new boolean[aList.size()];
        setQueue();
        setTResult();
    }
    
    // constructor for dfs traversal
    // *************************************************************************
    public AListTraversal(ArrayList<LinkedList<Integer>> aList, Stack<Integer> stack){
        this.visited = new boolean[aList.size()];
        setStack(stack);
        setTResult();
    }
    
    // setters and getters
    // *************************************************************************

    
    public void setQueue(){
        this.queue = new LinkedList<>();
    }
    
    public void setStack(Stack<Integer> stack){
        this.stack = stack;
    }    
    
    public void setTResult(){
        this.traversalResult = new ArrayList<>();
    }
    
    public boolean[] getVisited(){
        return this.visited;
    }
    
    public Queue<Integer> getQueue(){
        return this.queue;
    }
    
    public ArrayList<LinkedList<Integer>> getTraversalResult(){
        return this.traversalResult;
    }
    
    // Method: BFS traversal
    // *************************************************************************
    public void bfsTraversal(int vertex, ArrayList<LinkedList<Integer>> aList){
        // temporary LinkedList to add to the final array
        LinkedList<Integer> list = new LinkedList<>();
        
        // starting the visited for the cluster
        this.visited[vertex] = true;
        // starting the queue for the cluster
        this.queue.offer(vertex);
        
        while(!queue.isEmpty()){
            // update the vertex to the first element in queue
            vertex = queue.poll();
            // store the vertex in the bfs result list
            list.add(vertex);
            
            for(int num: aList.get(vertex)){
                if(!visited[num]){
                    visited[num] = true;
                    queue.offer(num);
                }
            }
        }
        // add traversalResult to bfs result list
        this.traversalResult.add(list);
               
        // restart vertex to the next element that has not been visited
        vertex = undiscoveredCheck();
        // recursion for new cluster
        if(vertex >= 0) bfsTraversal(vertex, aList);
    }
    
    // method: DFS Traversal
    // *************************************************************************
    public void dfsTraversal(int vertex, ArrayList<LinkedList<Integer>> aList, LinkedList<Integer> tList){
        
        // update the visited status
        if(!visited[vertex]) this.visited[vertex] = true;
        
        // add the current vertex to the temporary list variable
        tList.add(vertex);
        
        // begin to traverse through each level of each node
        for(int num: aList.get(vertex)){
            // if the node has not been visited, then use recursion to check if there is another level below it
            if(!visited[num]){
                this.stack.add(num);
                dfsTraversal(num, aList, tList);
                this.stack.pop();
            }
        }
        
        // once the stack is done, check if there are any undiscovered nodes
        if(stack.isEmpty()){
            // add the list to the result array
            this.traversalResult.add(tList);
            
            vertex = undiscoveredCheck();
            if(vertex >= 0) dfsTraversal(vertex, aList, new LinkedList<Integer>());
        }
    }
    
    // method: check if there are any undiscovered nodes and return the index or vertex
    // *************************************************************************
    public int undiscoveredCheck(){
        for(int i = 0; i < this.visited.length; i++){
            if(!this.visited[i]) return i;
        }
        return -1;
    }
}