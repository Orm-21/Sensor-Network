public class Node {
    private int x;
    private int y;
    
    public Node(int x, int y){
        setX(x);
        setY(y);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public String toString(){
        return String.format("(%s, %s)", this.x, this.y);
    }
    
}
