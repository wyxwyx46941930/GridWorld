import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

public class JumperTest {
    // test if can judge move or not 
    @Test
    public void canMoveTest() {
        final int x = 5, y = 5;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        assertTrue("Failure - can move", jumper.canMove());

        Rock rock = new Rock();
        world.add(new Location(x-2, y), rock);
        assertFalse("Failure - can not move", jumper.canMove());

        Jumper jumper2 = new Jumper();
        world.add(new Location(0, 0), jumper2);
        assertFalse("Failure - can not move due to the edge", jumper2.canMove());
    }

    // tests can move or not  
    @Test
    public void moveTest() {
        final int x = 5, y = 5;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.move();
        assertEquals("Failure - jumper should move forward with a distance of 2", x-2, jumper.getLocation().getRow());
        assertEquals("Failure - jumper should move forward with a distance of 2", y, jumper.getLocation().getCol());
    }

    // test can turn or not 
    @Test
    public void turnTest() {
        final int x = 5, y = 5;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.turn();
        assertEquals("Failure - jumper should turn 45 degrees to the right", Location.NORTHEAST, jumper.getDirection());
    }

    //test can act or not
    @Test
    public void actTest() {
        final int x = 5, y = 5;
        Jumper jumper = new Jumper();
        ActorWorld world = new ActorWorld();
        world.add(new Location(x, y), jumper);
        jumper.act();
        assertEquals("Failure - jumper should move forward", x-2, jumper.getLocation().getRow());

        Rock rock = new Rock();
        world.add(new Location(x-2-2, y), rock);
        jumper.act();
        assertEquals("Failure - jumper should NOT move forward", x-2, jumper.getLocation().getRow());
        assertEquals("Failure - jumper should turn 45 degrees to the right", Location.NORTHEAST, jumper.getDirection());
    }
}
