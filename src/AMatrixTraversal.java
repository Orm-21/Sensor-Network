import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AMatrixTraversal {
    int[][] matrix;
    private ArrayList<LinkedList<Integer>> traversalResult;
    int numNodes;


    public AMatrixTraversal(int[][] matrix, int numNodes) {
        this.matrix = matrix;
        this.numNodes = numNodes;
        traversalResult = new ArrayList<>();
    }

    public void BFS(int start) {
        // vertex is visited at the beginning
        boolean[] visited = new boolean[this.numNodes];
        Arrays.fill(visited, false);
        List<Integer> q = new ArrayList<>();

        q.add(start);
        // Set source as visited
        visited[start] = true;
 
        int vis;
        while (!q.isEmpty())
        {
            vis = q.get(0);
            // start a new cluster
            LinkedList<Integer> cluster = new LinkedList<>();
 
            // add the current node to the current cluster
            cluster.add(vis);
            q.remove(q.get(0));
 
            // For every adjacent vertex to the current vertex
            for(int i = 0; i < this.numNodes; i++)
            {
                if (matrix[vis][i] == 1 && matrix[i][vis] == 1 && (!visited[i]))
                {
                    q.add(i);
                    visited[i] = true;
                    cluster.add(i);
                }
            }
            // add the current cluster to the traversalResult ArrayList of clusters
            traversalResult.add(cluster);
        }
    }

        // function to perform DFS on the graph
    public void DFS(int start, boolean[] visited, LinkedList<Integer> cluster){ 
        // Set current node as visited
        visited[start] = true;
        cluster.add(start);
 
        // For every node of the graph
        for (int i = 0; i < this.numNodes; i++) {
            // If some node is adjacent to the current node and it has not already been visited
            if (!visited[i] && matrix[start][i] == 1 && matrix[i][start] == 1) {
                DFS(i, visited, cluster);
            } else {
                continue;
            }
        }
        // traversalResult.add(cluster);
    }

    public ArrayList<LinkedList<Integer>> getTraversalResult() {
        return this.traversalResult;
    }
}