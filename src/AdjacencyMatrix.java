import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class AdjacencyMatrix {
    int[][] matrix;
    int size;
    int tRange;
    NodeArray nodes;
    int nodeSize;
    ArrayList<Integer> component;
    Random rand;

    public AdjacencyMatrix(int size, int tRange, NodeArray n, int nodeSize) {
        this.size = size;
        this.tRange = tRange;
        this.nodes = n;
        this.nodeSize = this.nodes.getNumNodes();
        this.matrix = new int[size][size];
        this.component = new ArrayList<>();
        // sets all values that have a connection to equal 1, and all others to 0
        initalizeMatrix();
    }

    // distance between two Nodes
    public double getDistance(Node a, Node b){
        double result = Math.pow((a.getX() - b.getX()),2) + Math.pow((a.getY() - b.getY()), 2);
        return Math.sqrt(result);
    }

    // function to set all matrix values to 0
    private void initalizeMatrix() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                matrix[i][j] = 0;
            }
        }

        int index = 0;
        // going through all Nodes in NodeArray
        while (index < nodes.getNumNodes()) {
            // start with the first Node
            Node current = nodes.getNode(index);
            // compare this Node to all other Nodes in NodeArray
            for (int k = 0; k < nodes.getNumNodes(); k++) {
                if (index == k) {
                    matrix[index][k] = 1;
                        matrix[k][index] = 1;
                } else if (index != k) { // avoid checking itself
                    // get the distance between the nodes
                    double distance = getDistance(current, nodes.getNode(k));
                    // establish connection if nodes' distance are within tRange, set their undirected matrix values to equal 1
                    if (distance <= tRange) {
                        matrix[index][k] = 1;
                        matrix[k][index] = 1;
                    }
                }
            }
            index++;
        }

    }

    // ––––– DFS Traversal –––––
    public void dfs(int start, boolean[] visited) {
        dfsRecursive(start, visited, this.component);
    }

    public void dfsRecursive(int current, boolean[] visited, ArrayList<Integer> components) {
        // set the current Node's visited attribute as true
        visited[current] = true;
        // add the current Node to our cluster
        components.add(current);
        for (int i = 0; i < matrix[current].length; i++) {
            // If some node is adjacent to the current node
            // and it has not already been visited
            if (matrix[current][i] == 1 && matrix[i][current] == 1 && (!visited[i])) {
                dfsRecursive(i, visited, this.component);
            }
        }
    }

    public int undiscoveredCheck(boolean[] visited){
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]) return i;
        }
        return -1;
    }

    // BFS Traversal
    public void bfs(int start, boolean[] visited) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[start] = true;
        queue.add(start);

        while(queue.size() != 0) {
            start = queue.poll();
            this.component.add(start);
            
            for (int i = 0; i < matrix[start].length; i++){
                if (i < visited.length){
                    if (matrix[start][i] == 1 && matrix[i][start] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }

    }

    // randomly select a node from the components (post-traversal) to be the rendevouz point
    public Node rendevouzPoint() {
        rand = new Random();
        int randomInt = rand.nextInt(component.size());
        return nodes.getNode(randomInt);
    }

    // PRINT METHOD
    public void print() {
        System.out.println("Number of Connected Components: " + component.size());
        if (component.size() == nodes.getNumNodes()) {
            System.out.println("The Graph is Connected, there are " + component.size() + " connected satellites.");
            for (int i = 0; i < this.component.size(); i++) {
                System.out.println("[" + this.component.get(i) + "]");
            }
        } else {
            System.out.println("The Graph is Not Connected, there are only " + component.size() + " connected satellites.");
            for (int i = 0; i < this.component.size(); i++) {
                System.out.println("[" + this.component.get(i) + "]");
            }
        }
    }
}
