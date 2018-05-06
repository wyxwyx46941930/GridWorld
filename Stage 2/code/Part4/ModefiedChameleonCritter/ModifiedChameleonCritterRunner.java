//make this code in 2018-04-22
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
//have a new windows
final class ModifiedChameleonCritterRunner
{
    // deal with the magic number 
    private static final int FIVE = 5, EIGHT = 8;
    public static void main(String[] args)
    {
	// add a new test
        ActorWorld world = new ActorWorld();
        world.add(new Location(FIVE, EIGHT), new ModifiedChameleonCritter());
	// to show the test
        world.show();
    }
}
