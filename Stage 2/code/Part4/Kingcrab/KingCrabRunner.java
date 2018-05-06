import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
final class KingCrabRunner
{
    static final int SEVEN = 7, THREE = 3, TWO = 2, FIVE=5, EIGHT=8 , ZERO = 0;
    private KingCrabRunner() {
        // do nothing
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, THREE), new Rock(Color.YELLOW));
        world.add(new Location(TWO, THREE), new Rock(Color.YELLOW));
        world.add(new Location(THREE, THREE), new Rock(Color.YELLOW));
        world.add(new Location(THREE,FIVE) ,new Rock(Color.YELLOW));
        world.add(new Location(ZERO, THREE), new Rock(Color.YELLOW));
        world.add(new Location(FIVE, TWO), new Flower(Color.YELLOW));
        world.add(new Location(TWO, EIGHT), new KingCrab());
        world.show();
    }
}