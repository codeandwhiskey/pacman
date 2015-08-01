package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.services.*;
/*
*//**
 * Это реализация привидения.
 * 
 *//*
public class Casper extends PointImpl implements Tickable, State<Elements, Player> {

    private Field field;
    private boolean alive;
    private Direction direction;

    public Casper(Point xy) {
        super(xy);
        direction = null;
        alive = true;
    }

    public void init(Field field) {
        this.field = field;
    }

    
    public void down() {
        if (!alive) return;

        direction = Direction.DOWN;
    }

    
    public void up() {
        if (!alive) return;

        direction = Direction.UP;
    }

    
    public void left() {
        if (!alive) return;

        direction = Direction.LEFT;
    }

   
    public void right() {
        if (!alive) return;

        direction = Direction.RIGHT;
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
        }

        if (this == casper.getCasper()) {
            return Elements.HERO;
        } else {
            return Elements.OTHER_HERO;
        }
    }
}
*/