import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
public final class JumperRunner
{
    //set default constructor
    private JumperRunner() 
    {
    }
    public static void main(String[] args)
    {
        final int x = 5, y = 5 ;
        ActorWorld world = new ActorWorld() ;
        Jumper newjumper = new Jumper() ;
        Rock newrock = new Rock() ;
        world.add(new Location(x, y), newjumper) ;
        world.add(new Location(x, y-2), newrock) ;
        world.show() ;
    }
}
