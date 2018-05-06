import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
    private int[] turn;
    private int steps;
    private int count = 0;

    // default constructor
    public DancingBug() 
    {
        final int number1 = 1, number2 = 2, number3 = 3 ;
        turn = new int[3] ;
        turn[0] = number1 ;
        turn[1] = number2 ;
        turn[2] = number3 ;
        steps = 0 ;
    }
    //with parameter constructor
    public DancingBug(int[] turnTemp) 
    {
        turn = new int[turnTemp.length] ;
        //copy array from turnTemp to turn
        System.arraycopy(turnTemp, 0, turn, 0, turnTemp.length) ;
        steps = 0 ;
    }
    // dancing bug begin to act
    public void act() 
    {
        int index = steps % turn.length ;
        //set qumo hanshu
        if (count < turn[index]) 
        {
            turn() ;
            ++count ;
        } 
        //else do other thing
        else 
        {
            if (canMove()) 
            {
                move() ;
            } 
            else 
            {
                turn() ;
            }
            count = 0 ;
            ++steps ;
        }
    }
}
