package info.gridworld.maze;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        //UnboundedGrid ugr=new UnboundedGrid();
        ActorWorld world = new ActorWorld(); 
        MazeBug2 mb2 = new MazeBug2();
        world.add(new Location(3, 3), mb2);
        world.add(new Location(0,0), new MazeBug());
        world.add(new Location(1,1),new Rock());
        world.show();
    }
}
