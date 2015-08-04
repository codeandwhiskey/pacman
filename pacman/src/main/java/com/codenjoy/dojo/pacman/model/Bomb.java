package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

/**
 * Артефакт Бомба на поле
 */
public class Bomb extends PointImpl implements State<Elements, Player> {

    public Bomb(int x, int y) {
        super(x, y);
    }

    /*public Bomb(Point point) {
        super(point);
    }*/

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements./*HOLLAND_*/COOKIE;//was BOMB - TODO
    }
}
