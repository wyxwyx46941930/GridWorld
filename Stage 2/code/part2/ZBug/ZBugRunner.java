import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
public final class ZBugRunner
{
    //set a default constructor 
    private ZBugRunner() {
        // do nothing
    }
    public static void main(String[] args)
    {
        final int x = 5, y = 5;
        ActorWorld world = new ActorWorld();
        final int sizeOfZbug = 4;
        ZBug newZbug = new ZBug(sizeOfZbug);
        world.add(new Location(x, y), newZbug);
        world.show();
    }
}
