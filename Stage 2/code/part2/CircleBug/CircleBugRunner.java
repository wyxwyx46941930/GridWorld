import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
public final class CircleBugRunner
{   
    
    //provide a default constructor
    private CircleBugRunner() 
    {

    }
    public static void main(String[] args)
    {
         // finish the initial
        final int x = 5, y = 5, radius = 2;
        Rock myrock = new Rock();
        ActorWorld world = new ActorWorld();
        CircleBug newbug = new CircleBug(radius);
        world.add(new Location(x, y), newbug);
        world.add(new Location(x-1, y), myrock);
        world.show();
    }
}
