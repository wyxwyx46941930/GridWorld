import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
final class QuickCrabRunner
{
    private static final int SEVEN = 7, THREE = 3, TWO = 2,
                             EIGHT = 8, FIVE = 5;
    private QuickCrabRunner() {
        // do nothing
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, THREE), new Rock(Color.YELLOW));
        world.add(new Location(SEVEN, TWO), new Flower(Color.YELLOW));
        world.add(new Location(FIVE, EIGHT), new QuickCrab());
        world.show();
    }
}