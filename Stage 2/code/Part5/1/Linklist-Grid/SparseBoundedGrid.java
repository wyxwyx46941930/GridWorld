//use the linklist to implement the grid
//finish in 2018-04-22
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{	
	//set a my_own array
private SparseGridNode[] sparseArray;
private int cols;
private int rows;
//set a constructor
public SparseBoundedGrid(int rows, int cols) {
    if (rows <= 0) {
        throw new IllegalArgumentException("rows <= 0");
    }
    if (cols <= 0) {
        throw new IllegalArgumentException("cols <= 0");
    }
    this.sparseArray = new SparseGridNode[rows];
    this.rows = rows;
    this.cols = cols;
}

public int getNumRows() {
    return rows;
}
public int getNumCols() {
    return cols;
}
public boolean isValid(Location loc) {
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
//find the position have been occupied
public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> theLocations = new ArrayList<Location>();
    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++) {
        if (sparseArray[r] == null) {
            continue;
        } else {
            // If there's an object at this location, put it in the array.
            SparseGridNode sn = sparseArray[r];
            while (sn != null) {
                Location loc = new Location(r, sn.getCol());
                theLocations.add(loc);
                sn = sn.getNext();
            }
        }
    }
    return theLocations;
}
//set a get method 
public E get(Location loc) {
    if (loc == null) {
        throw new IllegalArgumentException("loc == null");
    }
    if (!isValid(loc)) {
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    }
    // find the postion is valid or not
    SparseGridNode sn = sparseArray[loc.getRow()];
    while (sn != null) {
        if (sn.getCol() == loc.getCol()) {
            return (E) sn.getOccupant();
        }
        sn = sn.getNext();
    }
    // if not find return null
    return null;
}
// Add the object to the grid.
public E put(Location loc, E obj) {
    if (loc == null) {
        throw new IllegalArgumentException("loc == null");
    }
    if (!isValid(loc)) { 
        throw new IllegalArgumentException("Location " + loc
                            + " is not valid");
    }
    if (obj == null) {
        throw new NullPointerException("obj == null");
    }
    //judge is the position is occucpied
    E oldOccupant = get(loc);
    SparseGridNode sn = sparseArray[loc.getRow()];
    
    SparseGridNode newSn = new SparseGridNode(obj, loc.getCol(), sn);
    sparseArray[loc.getRow()] = newSn;
    return  oldOccupant;
}
// Remove the object from the grid.
public E remove(Location loc) {
    if (loc == null) {
        throw new IllegalArgumentException("loc == null");
    }
    if (!isValid(loc)) {
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
        }
        E r = get(loc);
        //ffrst step to find the actors
        SparseGridNode sn = sparseArray[loc.getRow()];
        if (sn == null) {
            return r;
        }
        //first to find the col
        if (sn.getCol() == loc.getCol()) {
            sparseArray[loc.getRow()] = sn.getNext();
            return r;
        }
        SparseGridNode pre = sn;
        //find the next position
        while (sn != null) {
            if (sn.getCol() == loc.getCol()) {
                break;
            }
            pre = sn;
            sn = sn.getNext();
        }
        if (pre != null) {
            pre.setNext(sn.getNext());
            sn = null;
        }
        return r;
    }
}