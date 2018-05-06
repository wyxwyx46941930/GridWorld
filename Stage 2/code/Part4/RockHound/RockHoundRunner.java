// make this code in 2018-04-22
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import java.awt.Color;
final class RockHoundRunner
{
    //divide the magic number
    private static final int SEVEN = 7, THREE = 3, TWO = 2, FIVE = 5, EIGHT = 8;
    //set default constructor
    private RockHoundRunner() {
        // do nothing
    }
    public static void main(String[] args)
    {
	//add new position
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, THREE), new Rock(Color.YELLOW));
        world.add(new Location(SEVEN, TWO), new Flower(Color.YELLOW));
        world.add(new Location(FIVE, EIGHT), new RockHound());
        world.show();
    }
}
