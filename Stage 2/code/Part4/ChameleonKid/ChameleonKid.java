import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
// this part is used to change the color in front of it or behind it 
public class ChameleonKid extends ModifiedChameleonCritter {
    //Override the getActors method , then get the actors that be in front of or behind
  
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs))
        {
        	//find the not null actors
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }
    // The function is used to find the next position
     
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        //find the position
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
            	//find the valid position 
                locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}