import info.gridworld.actor.Bug;

public class CircleBug extends Bug 
{
    //set now step
    private int steps;
    //set circle limit
    private int radius;

    /**
     * Constructs a box bug that traces a circle of a given radius
     * @param radiusTmp the radius of the circle
     */
    public CircleBug(int radiusTemp) 
    {
        steps = 0;
        radius = radiusTemp;
    }
    /**
     * Moves to the next location of the circle
     */
    public void act() 
    {
        if (steps < radius && canMove()) 
        {
            move();
            ++steps;
        } 
        else 
        {
            turn();
            steps = 0;
        }
    }
}
