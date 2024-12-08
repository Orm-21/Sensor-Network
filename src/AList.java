import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class AList {
    private ArrayList<LinkedList<Integer>> aList; // Adjacency List
    
    
    public AList(NodeArray nodes, int tRange){
        setAList(nodes, tRange);
    }
    
    // Method: instantiate adjacency List and link up the nodes in aList
    public void setAList(NodeArray nodes, int tRange){        
        int index = 0;
        
        // instantiate the AList array
        this.aList = new ArrayList<>();
        
        while(index < nodes.size()){
            
            // instantiate the current node
            Node current = nodes.getNode(index);
            
            // instantiate the first linkedlist
            LinkedList<Integer> list = new LinkedList<>();
            
            // todo: maybe delete, this is to check if 0x0 was submitted
            if(current == null) break;
            
            // for loop to compare each node to current node
            for(int i = 0; i < nodes.size(); i++){
                if(index != i){
                    double distance = nodes.getDistance(current, nodes.getNode(i));
                    // if distance is less than the T-range
                    if(distance <= tRange){
                        // add node to linkedlist
                        list.add(i);
                    }
                }                    
            }
            
            // add linkedList to arrayList
            this.aList.add(list);
            // index equals the first index of the ArrayList not free
            index++;
        }        
    }
    
    // Method: bfs traversal and print
    // *************************************************************************
    public void bfsTraversal(int startNum){
        
        // initiate the bfs traversal class
        AListTraversal bfs = new AListTraversal(this.aList);
        
        // call traversal
        bfs.bfsTraversal(startNum, this.aList);
        
        //print the output message
        print(bfs, "Executing BFS Algorithm");
    }
    // Method: dfs traversal and print
    // *************************************************************************
    public void dfsTraversal(int startNum){
        
        // initiate the dfs traversal class
        AListTraversal dfs = new AListTraversal(this.aList, new Stack<Integer>());
        
        // call the traversal
        dfs.dfsTraversal(startNum, aList, new LinkedList<Integer>());
        
        // print the output message
        print(dfs, "Executing DFS Algorithm");
    }
    
    public ArrayList<LinkedList<Integer>> getAList(){
        return this.aList;
    }
    
    // Method: print traversal
    // *************************************************************************
    public void print(AListTraversal bfs, String s){
        String is = bfs.getTraversalResult().size() == 1? "is":"is not";
        System.out.printf("""
                          \n%s
                          Graph %s connected
                          There are/is %d connected component(s)
                          """, s, is, bfs.getTraversalResult().size());
        
        // print lists
        for(LinkedList<Integer> li: bfs.getTraversalResult()){
            System.out.printf("%s\n", li);
        }
    }

}
