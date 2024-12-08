import java.lang.Math;
import java.util.LinkedList;

public class AMatrix {
    int[][] matrix;
    int tRange;
    NodeArray nodes;
    int numNodes;
    LinkedList<Integer> dfsResult;


    public AMatrix(int tRange, NodeArray n, int size) {
        matrix = new int[size][size];
        this.tRange = tRange;
        nodes = n;
        this.numNodes = size;
        // instantiate matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        // add nodes to the matrix;
        addNodes();
    }

    public void addNodes() {
        for (int i = 0; i < this.numNodes; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                if (getDistance(nodes.getNode(i), nodes.getNode(j)) <= tRange) {
                   addEdge(nodes.getNode(i), nodes.getNode(j));
                }
            }
        }
    }
    

    // graph is undirected, add conenction both directions if range <= tRange
    public void addEdge(Node source, Node dest) {
        matrix[nodes.getIndex(source)][nodes.getIndex(dest)] = 1;
        matrix[nodes.getIndex(dest)][nodes.getIndex(source)] = 1;
    }

    public boolean checkEdge(int source, int dest) {
        if (matrix[source][dest] == 1) {
            if(matrix[dest][source] == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // distance formula to get distance between two Nodes
    public double getDistance(Node a, Node b){
        double result = Math.pow((a.getX() - b.getX()),2) + Math.pow((a.getY() - b.getY()), 2);
        return Math.sqrt(result);
    }

    public void BFStraversal() {
        AMatrixTraversal bfs = new AMatrixTraversal(matrix, numNodes);
        bfs.BFS(tRange);
        printBFS(bfs, "Executing BFS traversal: \n");
    }

    public void DFStraversal() {
        AMatrixTraversal dfs = new AMatrixTraversal(matrix, this.numNodes);
        boolean[] visited = new boolean[this.numNodes];
        dfsResult = new LinkedList<>();
        dfs.DFS(0, visited, dfsResult);
        printDFS(dfs, "Executing DFS traversal: \n");
    }


    public void printBFS(AMatrixTraversal bfs, String s){
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

    public void printDFS(AMatrixTraversal bfs, String s){
        String is = dfsResult.size() == 1? "is":"is not";
        System.out.printf("""
                          \n%s
                          Graph %s connected
                          There are/is %d connected component(s)
                          """, s, is, dfsResult.size());
        
        // print lists
        System.out.println(dfsResult);
    }


    
}
