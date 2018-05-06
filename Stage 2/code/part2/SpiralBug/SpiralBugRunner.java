import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class SpiralBugRunner
{
    public static void main(String[] args)
    {
        // finish the initial
        final int x = 5, y = 5;
        ActorWorld world = new ActorWorld();
        SpiralBug new_spiralbug = new SpiralBug();
        world.add(new Location(x, y), new_spiralbug);
        world.show();
    }
    //provide a default constructor
    private SpiralBugRunner() 
    {
        
    }
}
