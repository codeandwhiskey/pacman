package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;
import com.codenjoy.dojo.services.Tickable;
/*
/**
 * Это реализация привидения.
 * 
 */
public class Casper extends PointImpl implements Tickable, State<Elements, Player>{
	
	private Field field;
    private boolean alive;
    private Direction direction;

	public Casper(Point xy) {
        super(xy);
		direction = Direction.DOWN;
        alive = true;
    }

    public Casper(int x, int y) {	
		super(x, y);
	}

	public void init(Field field) {
        this.field = field;
    }
	
	public Direction getDirection() {
		return direction;
	}


    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.CASPER;
    }
    
	@Override
	public void tick() {		
		//if (!alive) return;

        //if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);
            
           if (!field.isBarrier(newX, newY)) {
            	
               move(newX, newY);
           } else {
        	 direction =  Direction.STOP;
           }
       //}
      //direction = null;
		
	}


	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	

  
    }

