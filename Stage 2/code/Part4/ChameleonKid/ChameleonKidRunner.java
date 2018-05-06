import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;
final class KidRunner
{
    //set default constructor 
    private KidRunner() {
        // do nothing
    }
    //deal with the magic number
    static final int SEVEN = 7, TWO = 2, FIVE = 5, EIGHT = 8;
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, TWO), new Rock(Color.YELLOW));
        world.add(new Location(FIVE, EIGHT), new ChameleonKid());
        world.show();
    }
}