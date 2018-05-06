// date 2018-05-04
// code by wyx
package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

// implement a method which could to move to the right door
public class MazeBug extends Bug {
    public Location next;
    public Location last;
    public boolean isEnd = false;
    public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    public Integer stepCount = 0;
    boolean hasShown = false; //final message has been shown

    //construct a constructor to accomplish the method 
    public MazeBug() {
        setColor(Color.GREEN);
        last = null;
        next = null;
    }
    //move to the next location of the square
    public void act() {
        boolean willMove = canMove();
        if (isEnd == true) {
        //to show step count when reach the goal        
            if (hasShown == false) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        } else {
            back();
            stepCount++;
        }
    }

    // find a valid position to have a move
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        int[] dirs = { Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
        if (loc == null) {
            return valid;
        }
        for (int d : dirs) {
        	// find the four direction
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (gr.isValid(neighborLoc) ) {
                Actor actor = gr.get(neighborLoc);
                if (actor == null) {
                    valid.add(neighborLoc);
                } else if ( (actor instanceof Rock) && (actor.getColor().equals(Color.RED)) ) {
                    valid.add(neighborLoc);
                }
            }
        }
        return valid;
    }

    // test whether this bug can move forward into a location that is empty or contains a flower.
    // return true if this bug can move.
 
    public boolean canMove() {
        ArrayList<Location> locs = getValid(getLocation());
        if (locs.size() == 0) {
            return false;
        }
        return true;
    }
    //moves the bug forward, putting a flower into the location it previously occupied.
    public void move() {
        if (stepCount == 0) {
            ArrayList<Location> firstStep = new ArrayList<Location>();
            firstStep.add(getLocation());
            crossLocation.push(firstStep);
        }
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        selectNext();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            // If the next is the rock, then set isEnd as true
            if (gr.get(next) instanceof Rock) {
                isEnd = true;
            }
            moveTo(next);
            ArrayList<Location> newTop = new ArrayList<Location>();
            newTop.add(next);
            crossLocation.push(newTop);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    // move back to last position
    public void back() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        if (crossLocation.size() == 0) {
            return;
        }
        crossLocation.pop();
        if (crossLocation.size() == 0) {
            return;
        }
        Location loc = getLocation();
        //find the last position
        ArrayList<Location> lastArr = crossLocation.peek();
        next = lastArr.get(0);
        setDirection(getLocation().getDirectionToward(next));
        moveTo(next);
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    //select a location that the bug can move to; 
    //if the location is the red rock, then select it; else randomly select a location
    
    public void selectNext() {
        Location loc = getLocation();
        ArrayList<Location> valids = getValid(loc);
        Grid<Actor> grid = getGrid();
        //get a new grid
        if (valids.size() > 0) {
            for (Location location : valids) {
                if (grid.get(location) instanceof Rock) {
                    next = location;
                    return;
                }
            }
            //find the math random 
            int r = (int)(Math.random()*valids.size());
            next = valids.get(r);
        }
    }
}
