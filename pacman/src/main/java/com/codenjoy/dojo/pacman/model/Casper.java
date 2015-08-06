package com.codenjoy.dojo.pacman.model;

import java.util.List;

import com.codenjoy.dojo.services.*;
/*
/**
 * Это реализация привидения.
 * 
 */
public class Casper extends PointImpl implements Joystick, Tickable, State<Elements, Player>{
	
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
		if (!alive) return;

        if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);

            if (!field.isBarrier(newX, newY)) {
                move(newX, newY);
            }
        }
        direction = null;
		
	}
	
	@Override
    public void down() {
        if (!alive) return;
        direction = Direction.DOWN;
        
    }

    @Override
    public void up() {
        if (!alive) return;

        direction = Direction.UP;
    }

    @Override
    public void left() {
        if (!alive) return;

        direction = Direction.LEFT;
    }

    @Override
    public void right() {
        if (!alive) return;

        direction = Direction.RIGHT;
    }

    
	@Override
	public void act(int... p) {
		// TODO Auto-generated method stub
		
	}


	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

  
    }

