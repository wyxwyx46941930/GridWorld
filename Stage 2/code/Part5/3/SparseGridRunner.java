import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
//This class runs a world with additional grid choices.

public class SparseGridRunner
{
  public static void main(String[] args)
  {
	//add a new method 
    ActorWorld world = new ActorWorld();
    // add a liklist 
    world.addGridClass("SparseBoundedGrid");
    // add a hashmap
    world.addGridClass("SparseBoundedGrid2");
    // add a no-side map
    world.addGridClass("UnboundedGrid2");
    world.add(new Location(2, 2), new Critter());
    world.show();
  }
}