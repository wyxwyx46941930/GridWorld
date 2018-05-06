import info.gridworld.actor.Bug;

public class ZBug extends Bug {
    //set now step
    private int steps;
    //set step limit
    private int length;

    public ZBug(int lengthTemp) 
    {
        steps = 0;
        length = lengthTemp;
        //make bug face east
        turn();
        turn();
    }
    //judge first time arrive z brid
    private boolean FirstTurn() 
    {
        return (steps == length);
    }

    // judge second time arrive z bird 
    private boolean SecondTurn() 
    {
        return (steps == 2*length+1);
    }
    // judge 
    private boolean Stop() 
    {
        final int three = 3;
        return (steps >= three*length+2 || !canMove());
    }
    // bug move according to the specified rule
    public void act() 
    {
        if (FirstTurn()) 
        {
            //deal the magic number problem
            final int three = 3;
            for (int i = 0; i < three; ++i) 
            {
                turn();
            }
        } 
        else if (SecondTurn()) 
        {
            final int five = 5;
            for (int i = 0; i < five; ++i) 
            {
                turn();
            }
        } 
        else if (Stop()) 
        {
            --steps;
        } 
        else if (canMove()) 
        {
            move();
        }
        ++steps;
    }
}
