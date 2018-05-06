//make this code in 2018-04-22
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import java.util.HashMap;
import java.util.ArrayList;
public class UnboundedGrid2<E> extends AbstractGrid<E> 
{

    private Object[][] occupantArray;
    private final static int defaultSize = 16;
    private int size;
    // provide a constructor
    public UnboundedGrid2() 
    {
        this.size = defaultSize;
        this.occupantArray = new Object[defaultSize][defaultSize];
    }
    // provide a enough long row
    public int getNumRows() 
    {
        return -1;
    }
    // provide a enough long col
    public int getNumCols() 
    {
        return -1;
    }
    // judge the pisition is valid or not
    public boolean isValid(Location loc) 
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }
    // creat a new size
    public int getNewSize(Location loc) 
    {
        while (loc.getCol() >= size || loc.getRow() >= size) {
            size = size * 2;
        }
        return size;
    }
    // get all position
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int r = 0; r < size; r++) 
        {
            for (int c = 0; c < size; c++) 
            {
                Location loc = new Location(r, c);
                if (get(loc) != null) 
                {
                    locs.add(loc);
                }
            }
        }
        return locs;
    }
    // find a actors
    public E get(Location loc)
    {
        if (loc == null) 
        {
            throw new NullPointerException("loc == null");
        }
        if (loc.getCol() >= size || loc.getRow() >= size) 
        {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }
    // set a put method to put actors
    public E put(Location loc, E obj)
    {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        // double the array if needed
        if (loc.getCol() >= size || loc.getRow() >= size) 
        {
            int oldSize = size;
            int newSize = getNewSize(loc);
            Object[][] newArray = new Object[newSize][newSize];
            for (int i = 0; i < oldSize; i++) 
            {
                for (int j = 0; j < oldSize; j++) 
                {
                    newArray[i][j] = occupantArray[i][j];
                }
            }
            occupantArray = newArray;
        }
        // if you can find the positon set it
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    ///provide a method to remove actors
    public E remove(Location loc)
    {
        if (loc == null) 
        {
            throw new NullPointerException("loc == null");
        }
        if (loc.getCol() >= size || loc.getRow() >= size) 
        {
            return null;
        }
        //if find the position set it to null
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}