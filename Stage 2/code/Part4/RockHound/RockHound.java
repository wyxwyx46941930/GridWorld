import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

//this part is designed to eat all round 
class RockHound extends Critter {

    /**
     * Remove rocks in the actors.
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor act : actors) {
            if (act instanceof Rock) {
                act.removeSelfFromGrid();
            }
        }
    }
}
