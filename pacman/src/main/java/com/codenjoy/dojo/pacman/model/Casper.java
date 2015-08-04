package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.services.*;
/*
/**
 * Это реализация привидения.
 * 
 */
public class Casper /*extends PointImpl implements Tickable, State<Elements, Player> */extends PointImpl implements State<Elements, Player>{
	
    public Casper(int x, int y) {
        super(x, y);
    }
    
    

    public Casper(Point point) {
        super(point);
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.CASPER;
    }
    /*@Override
    public void move(int x, int y){
    	
    }

    private Field field;
    private boolean alive;
    private Direction direction;

    public Casper(Point xy) {
        super(xy);
        direction = getDirection();
        alive = true;
    }

    public void init(Field field) {
        this.field = field;
    }

    public Direction getDirection() {
        return direction;
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

    public boolean isAlive() {
        return alive;
    }
    
    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (!isAlive()) {
            return Elements.DEAD_HERO;
        } else {

       // if (this == player.getCasper()) {
            return Elements.CASPER;
        }*/
        //}
		//return null;
    }

