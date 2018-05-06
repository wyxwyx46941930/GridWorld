import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;

public class Jumper extends Actor {
    //set a constructor
    public Jumper() {
        setColor(Color.RED);
    }
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }
    //let the actor have a atrribute
    public void act() {
        if(canJump())
        {
            Jump();
        }
        else if (canMove()) 
        {
            move();
        } 
        else 
        {
            turn();
        }
    }

    ///let actor turn
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    // let actor move
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        //set a new positon
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) 
        {
            moveTo(next);
        } 
        //remove the actor
        else 
        {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr,loc);
    }
    // judge could be move or not
    public boolean canMove() {
        //new a things
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        //find a new postion
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) 
        {
            return false;
        }
        //find next position
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    //let actor jump
    public void Jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) 
        {
            return ;
        }
        //find a new position
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if(gr.isValid(next))
        {
            Location next2 = next.getAdjacentLocation(getDirection());
            if(gr.isValid(next2))
            {
                moveTo(next2);
            }
            // remove actor
            else
            {
                removeSelfFromGrid();
            }
        }
    }
    //judge could jump or not
    public boolean canJump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        //find a new sit
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next2 = next.getAdjacentLocation(getDirection());
        //if couldn't find do other things
        if(!gr.isValid(next2))
        {
            return false ;
        }
        Actor neighbor = gr.get(next2);
        return (neighbor == null || neighbor instanceof Flower);
    }
}
