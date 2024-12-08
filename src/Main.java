import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the size of the width/height of the graph: ");
        // for this simulation, we will have width and height be the same value
        int width = in.nextInt();
        int height = width;
        System.out.println("Please enter the transmission range: ");
        int tRange = in.nextInt();
        System.out.println("Enter the number of satellites: ");
        int numNodes = in.nextInt();

        // ––– CREATE NODES –––
        NodeArray nodes = new NodeArray(height, width, numNodes);
        nodes.setNode(height, width);

        // ––– TESTING NODES –––
        // print out all nodes, for testing purposes
        System.out.println("Total number of satellites: " + numNodes);
        for (int i = 0; i < numNodes; i++) {
            System.out.println("[" + (i+1) + "]: (" + nodes.getNode(i).getX() + ", " + nodes.getNode(i).getY() + ")");
        }
        System.out.println(); // spacers
        System.out.println();

        System.out.println("Choose the Graph Structure:");
        System.out.print("""
                         \n'1' for Adjacency Matrix
                         '2' for Adjacency List
                         Enter the preferred graph structure: """);
        int structure = in.nextInt();

        // ––––– INITALIZE ADJACENCY MATRIX –––––
        if(structure == 1){
            AMatrix matrix = new AMatrix(tRange, nodes, numNodes);
            
            System.out.print("""
                             \n'1' for BFS
                             '2' for DFS
                             Enter the preferred traversal method: """);
            int tMethod = in.nextInt();
            System.out.println();

            if(tMethod == 1){   // –––– Matrix BFS ––––
                matrix.BFStraversal();
            } else if (tMethod == 2) {  // –––– Matrix DFS ––––
                matrix.DFStraversal();
            }
        }
        //INITIALIZE ADJACENCY LIST
        else if(structure == 2){
            AList alist = new AList(nodes, tRange);
            
            System.out.print("""
                             \n'1' for BFS
                             '2' for DFS
                             Enter the preferred traversal method: """);
            int tMethod = in.nextInt();
            System.out.println();

            // call BFS method
            if(tMethod == 1){
                alist.bfsTraversal(0);
            }

            // todo: call DFS
            else if(tMethod == 2){
                alist.dfsTraversal(0);
            }
        }


        in.close();
        
    }
    
}
