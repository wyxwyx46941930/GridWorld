import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
//set a new method to make the critter move
public class QuickCrab extends CrabCritter {
    public ArrayList<Location> getMoveLocations() 
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        //set two choices to choose  
        int[] dirs = { Location.LEFT, Location.RIGHT };
        // first find the side could move 
        for (Location loc : getLocationsInTwoSides(dirs)) 
        {
            if (getGrid().get(loc) == null) 
            {
                locs.add(loc);
            }
        }
        //find the position could move
        if (locs.size() == 0) 
        {
            return super.getMoveLocations();
        }
        return locs;
    }
    //find if the critter could to move two steps
    public ArrayList<Location> getLocationsInTwoSides(int[] directions)
    {
        ArrayList<Location> locTmp = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc) && getGrid().get(neighborLoc) == null) 
            {
            	// find two steps
                Location neightborLoc2 = neighborLoc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neightborLoc2)) 
                {
                    locTmp.add(neightborLoc2);
                }
            }
        }
        return locTmp;
    }
}