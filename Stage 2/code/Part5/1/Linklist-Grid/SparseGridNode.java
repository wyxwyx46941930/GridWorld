//privide a new class for the Grid1
public class SparseGridNode
{
    private Object occupant;
    private int col;
    //point to nexz position
    private SparseGridNode next;
    //privide a constructor
    public SparseGridNode(Object occupant, int col, SparseGridNode next) {
        this.occupant = occupant;
        this.col = col;
        this.next = next;
    }
    //provide a set method 
    public void setOccupant(Object cp) {
        this.occupant = cp;
    }
    //provide a get method
    public Object getOccupant() {
        return occupant;
    }
    //provide a set method
    public void setCol(int col) {
        this.col = col;
    }
    //provide a get method
    public int getCol() {
        return col;
    }
    //provide a set method
    public void setNext(SparseGridNode next) {
        this.next = next;
    }
  //provide a get method
    public SparseGridNode getNext() {
        return next;
    }

}