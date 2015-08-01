package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

/**
 * Артефакт Золото на поле
 */
public class Cookie extends PointImpl implements State<Elements, Player> {

    public Cookie(int x, int y) {
        super(x, y);
    }

    public Cookie(Point point) {
        super(point);
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.COOKIE;
    }
}
