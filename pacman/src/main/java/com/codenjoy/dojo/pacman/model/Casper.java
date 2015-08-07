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
		direction = null;
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
		int i = (int)(Math.random()*5);
		if(i == 1){
			direction = Direction.UP;
		} else if(i == 2){
			direction = Direction.DOWN;
		} else if(i == 3){
			direction = Direction.RIGHT;
		} else if(i == 4){
			direction = Direction.LEFT;
		}
		//if (!alive) return;
        //if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);
            
           //if (!field.isBarrier(newX, newY)) {
            	
               move(newX, newY);
          // } else {
        	// direction =  Direction.STOP;
           //}
       }
      //direction = null;
		
	//}


	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	

  
    }

