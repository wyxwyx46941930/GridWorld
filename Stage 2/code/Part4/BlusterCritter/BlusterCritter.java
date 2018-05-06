import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;
public class BlusterCritter extends Critter {
    private int number;
    //here set a limit for color
    private static final double COLOR_FACTOR = 25;
    private static final int MAX_COLOR = 255;
    private static final int MIN_COLOR = 0;
    //set the limit of number_c
    public BlusterCritter(int n) {
        this.number = n;
    }
    //Get the actors which are within 2 steps the critter
 
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int row = getLocation().getRow(), col = getLocation().getCol();
        int up = row-2;
        int down = row+2;
        int left = col-2;
        int right = col+2;
        Grid<Actor> grid = getGrid();
	//judge if the world is empty
        if (grid == null) 
        {
            return actors;
        }
        //set a two times loop for look for the number of critter
        for (int i = left;  i <= right; i++) 
        {
            for (int j = up; j <= down; j++) 
            {
                if ( !(i == col && j == row) ) 
                {
                	//find the location
                    Location location = new Location(j, i);
                    if ( grid.isValid(location) ) 
                    {
                    	// add a new actor's position
                        Actor actor = grid.get(location);
                        if (actor != null) 
                        {
                            actors.add(actor);
                        }
                    }
                }
            }
        }
	//return the actors you have find 
        return actors;
    }
    //Brighten or Darken the critter according the number of actors

    public void processActors(ArrayList<Actor> actors)
    {
	// to count the number of actors
        int count = 0;
        for (Actor actor : actors) 
        {
            if (actor instanceof Critter) 
            {
                count++;
            }
        }
        if (count < number) 
        {
            colorBrighten();
        } 
        else 
        {
            colorDarken();
        }
    }
    //make the critter become bright
    private void colorBrighten() 
    {
        Color c = getColor();
        int red = (int)(c.getRed()+COLOR_FACTOR) < MAX_COLOR ? (int)(c.getRed()+COLOR_FACTOR) : MAX_COLOR;
        int green = (int)(c.getGreen()+COLOR_FACTOR) < MAX_COLOR ? (int)(c.getGreen()+COLOR_FACTOR) : MAX_COLOR;
        int blue = (int)(c.getBlue()+COLOR_FACTOR) < MAX_COLOR ? (int)(c.getBlue()+COLOR_FACTOR) : MAX_COLOR;
        setColor(new Color(red, green, blue));
    }
    //make the critter become dark
    private void colorDarken() 
    {
        Color c = getColor();
        int red = (int)(c.getRed()-COLOR_FACTOR) > MIN_COLOR ? (int)(c.getRed()-COLOR_FACTOR) : MIN_COLOR;
        int green = (int)(c.getGreen()-COLOR_FACTOR) > MIN_COLOR ? (int)(c.getGreen()-COLOR_FACTOR) : MIN_COLOR;
        int blue = (int)(c.getBlue()-COLOR_FACTOR) > MIN_COLOR ? (int)(c.getBlue()-COLOR_FACTOR) : MIN_COLOR;
        setColor(new Color(red, green, blue));
    }
}
