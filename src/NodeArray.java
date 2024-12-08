import java.util.*;
import java.lang.Math;

public class NodeArray {
    private Node nodes[];
    private boolean[][] exists;
    private int width;
    private int height;
    private int numNodes;
    
    public NodeArray(int width, int height, int numNodes){
        this.nodes = new Node[numNodes];
        this.width = width;
        this.height = height;
        this.numNodes = numNodes;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int randomizeX(int width) {
        Random randy = new Random();
        int rW = randy.nextInt(width);
        return rW;
    }

    public int randomizeY(int height) {
        Random randy = new Random();
        int rH = randy.nextInt(height);
        return rH;
    }

    public void setExists(int width, int height) {
        this.exists = new boolean[width][height];
        for (int a = 0; a < width; a++) {
            for (int b = 0; b < height; b++) {
                this.exists[a][b] = false;
            }
        }
    }

    public void setNode(int width, int height){
        // first, fill in exitst[][] so it is not null
        setExists(width, height);

        // go through all indices of our nodes[] array
        for(int i = 0; i < this.numNodes; i++){
            // generate a random (X, Y) value within the boundaries of our graph size
            int X = randomizeX(width);
            int Y = randomizeY(height);
            // check if this set of values already exists
            while (this.exists[X][Y]) {
                X = randomizeX(width);
                Y = randomizeY(height);
            }
            // add this new node to our nodes[] array
            this.nodes[i] = new Node(X, Y);
            // set this set as exists = True, to avoid duplicate objects
            this.exists[X][Y] = true;
        }
        this.numNodes = initLength(nodes);
    }

    public int initLength(Node[] myArray) {
        int count = 0;
        for (Node n : myArray) {
          if ( n != null ) count++;
        }
        return count;
      }

    public int size() {
        return this.nodes.length -1;
    }
    
    public Node getNode(int i){
        return this.nodes[i];
    }

    public Node[] getNodeArray(){
        return this.nodes;
    }
    
    public int getIndex(Node node){
        for(int i = 0; i < this.nodes.length; i++){
            if(node.equals(this.nodes[i])){
                return i;
            }
        }
        return -1;
    }

    // distance formula to get distance between two Nodes
    public double getDistance(Node a, Node b){
        double result = Math.pow((a.getX() - b.getX()),2) + Math.pow((a.getY() - b.getY()), 2);
        return Math.sqrt(result);
    }

    public int getNumNodes() {
        return this.numNodes;
    }
}
