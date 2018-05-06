import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class KingCrab extends CrabCritter {
	//first to find out if there any actor is in front of itself 
    public void processActors(ArrayList<Actor> actors) 
    {
        for (Actor a : actors) {
        	//delete it from grid
            if (!canActorFurtherAway(a)) 
            {
                a.removeSelfFromGrid();
            }
        }
    }
    private boolean canActorFurtherAway(Actor a) 
    {
    	//judge if could move or not
        int angel = getLocation().getDirectionToward(a.getLocation());
        Location furtherLoc = a.getLocation().getAdjacentLocation(angel);
        //if could move return true
        if (getGrid().isValid(furtherLoc) && getGrid().get(furtherLoc) == null) 
        {
        	//if can move away return true
            a.moveTo(furtherLoc);
            return true;
        }
        return false;
    }
}