import info.gridworld.actor.Bug;

public class SpiralBug extends Bug 
{
    //set now step
    private int steps;
    //set step limit
    private int sideLength;

    /**
     * Constructs a spiral bug that traces a spiral
     */
    public SpiralBug() 
    {
        final int initLength = 3;
        steps = 0;
        sideLength = initLength;
    }

    /**
     * Moves to the next location of the spiral
     */
    
    public void act() 
    {
        if (steps < sideLength && canMove()) 
        {
            move();
            ++steps;
        } 
        else if (steps < sideLength && !canMove()) 
        {
            // do nothing
        } 
        else 
        {
            turn();
            turn();
            steps = 0;
            ++sideLength;
        }
    }
}
