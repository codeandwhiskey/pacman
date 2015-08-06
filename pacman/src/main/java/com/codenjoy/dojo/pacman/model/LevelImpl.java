package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.pacman.model.Casper;
import com.codenjoy.dojo.pacman.model.Elements;
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Полезный утилитный класс для получения объектов на поле из текстового вида.
 */
public class LevelImpl implements Level {
    private final LengthToXY xy;

    private String map;

    public LevelImpl(String map) {
        this.map = map;
        xy = new LengthToXY(getSize());
    }

    @Override
    public int getSize() {
        return (int) Math.sqrt(map.length());
    }

    @Override
    public List<Hero> getHero() {
        List<Hero> result = new LinkedList<Hero>();

        for (Point pt : getPointsOf(Elements.HERO)) {
            result.add(new Hero(pt));
        }

        return result;
    }

    @Override
    public List<Cookie> getCookie() {
        List<Cookie> result = new LinkedList<Cookie>();

        for (Point pt : getPointsOf(Elements.COOKIE)) {
            result.add(new Cookie(pt));
        }

        return result;
    }
    
    @Override
    public List<HollandCookie> getHollandCookie() {
        List<HollandCookie> result = new LinkedList<HollandCookie>();

        for (Point pt : getPointsOf(Elements.HOLLAND_COOKIE)) {
            result.add(new HollandCookie(pt));
        }

        return result;
    }

    @Override
    public List<Wall> getWalls() {
        List<Wall> result = new LinkedList<Wall>();

        for (Point pt : getPointsOf(Elements.WALL)) {
            result.add(new Wall(pt));
        }

        return result;
    }
    
    @Override
	public List<Casper> getCasper() {
		List<Casper> result = new LinkedList<Casper>();

        for (Point pt : getPointsOf(Elements.CASPER)) {
            result.add(new Casper(pt));
        }

        return result;

    }

    private List<Point> getPointsOf(Elements element) {
        List<Point> result = new LinkedList<Point>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == element.ch) {
                result.add(xy.getXY(index));
            }
        }
        return result;
    }
}
