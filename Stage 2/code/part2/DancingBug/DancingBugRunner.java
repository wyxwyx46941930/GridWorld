import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
public final class DancingBugRunner
{
    //set a default constructor
    private DancingBugRunner() {
        // do nothing
    }
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        final int x = 5, y = 5;
        final int number1 = 1, number2 = 2, number3 = 3, number4 = 4;
        int[] turns = new int[4] ;
        turns[0] = number1 ; turns[1] = number2 ;
        turns[2] = number3 ; turns[3] = number4 ;
        DancingBug newDancingbug = new DancingBug(turns);
        world.add(new Location(x, y), newDancingbug);
        world.show();
    }
}
