import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Rock;
public class ModifiedChameleonCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, the color of the critter will darken.
     */
    private static final double DARKENING_FACTOR = 0.05;
    public void processActors(ArrayList<Actor> actors)
    {
    	// judge if the actors is zero
        int n = actors.size();
        if (n == 0) {
            darken();
            return;
        }
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }

    // have a turn when go to a new location as it moves.
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }

    private void darken() {
        Color c = getColor();
        int red = (int)(c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int)(c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int)(c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
}

