// use hash to implement the grid
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    //provide a parameter
	private int rowNum;
    private int colNum;
    private Map<Location, E> occupantHMap;
    //set a constructor
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        occupantHMap = new HashMap<Location, E>();
        this.rowNum = rows;
        this.colNum = cols;
    }

    public int getNumRows() {
        return rowNum;
    }
    public int getNumCols() {
        return colNum;
    }
    //judge is valid or not
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    // find the occupation
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (Location loc : occupantHMap.keySet()) {
            theLocations.add(loc);
        }
        return theLocations;
    }
    //set a get method 
    public E get(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        //judege if valid or not
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        return occupantHMap.get(loc);
    }
    //set a put method 
    public E put(Location loc, E obj) {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        //judge is valid or not
        if (!isValid(loc)) { 
            throw new IllegalArgumentException("Location " + loc
                                + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        return occupantHMap.put(loc, obj);
    }
    //set a remove method
    public E remove(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        return occupantHMap.remove(loc);
    }
}