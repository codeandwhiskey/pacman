package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.pacman.services.Events;
import com.codenjoy.dojo.services.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * О! Это самое сердце игры - борда, на которой все происходит.
 * Если какой-то из жителей борды вдруг захочет узнать что-то у нее, то лучше ему дать интефейс {@see Field}
 * Борда реализует интерфейс {@see Tickable} чтобы быть уведомленной о каждом тике игры. Обрати внимание на {Sample#tick()}
 */
public class Pacman implements Tickable, Field {

    private List<Wall> walls;
    private List<Cookie> cookie;
    private List<HollandCookie> hollandCookie;

    private List<Player> players;
    //private List<Ghost> ghosts;
    private Casper casper;

    private final int size;
    private Dice dice;

    public Pacman(Level level, Dice dice) {
        this.dice = dice;
        walls = level.getWalls();
        cookie = level.getCookie();
        hollandCookie = level.getHollandCookie();
        size = level.getSize();
        casper = level.getCasper();
        players = new LinkedList<Player>();
       // ghosts = new LinkedList<Ghost>();
    }

    /**
     * @see Tickable#tick()
     */
    @Override
    public void tick() {
        for (Player player : players) {
            Hero hero = player.getHero();

            hero.tick();
            

            if (cookie.contains(hero)) {
                cookie.remove(hero);
                player.event(Events.WIN);

            }
        }
       
 

        for (Player player : players) {
            Hero hero = player.getHero();

            if (!hero.isAlive()) {
                player.event(Events.LOOSE);
            }
        }
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isBarrier(int x, int y) {
        Point pt = PointImpl.pt(x, y);
        return x > size - 1 || x < 0 || y < 0 || y > size - 1 || walls.contains(pt) || getHeroes().contains(pt);
    }

    @Override
    public Point getFreeRandom() {
        int rndX = 0;
        int rndY = 0;
        int c = 0;
        do {
            rndX = dice.next(size);
            rndY = dice.next(size);
        } while (!isFree(rndX, rndY) && c++ < 100);

        if (c >= 100) {
            return PointImpl.pt(0, 0);
        }

        return PointImpl.pt(rndX, rndY);
    }

    @Override
    public boolean isFree(int x, int y) {
        Point pt = PointImpl.pt(x, y);

        return  !hollandCookie.contains(pt) &&
        		!walls.contains(pt) &&
        		!getHeroes().contains(pt);
    }

    public List<Cookie> getCookie() {
        return cookie;
    }
    
    public List<HollandCookie> getHollandCookie() {
        return hollandCookie;
    }

    public List<Hero> getHeroes() {
        List<Hero> result = new ArrayList<Hero>(players.size());
        for (Player player : players) {
            result.add(player.getHero());
        }
        return result;
    }

    public void newGame(Player player) {
        if (!players.contains(player) ) {
            players.add(player);
        }
        player.newHero(this);
        
     
    }

    public void remove(Player player) {
        players.remove(player);
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public BoardReader reader() {
        return new BoardReader() {
            private int size = Pacman.this.size;

            @Override
            public int size() {
                return size;
            }

            @Override
            public Iterable<? extends Point> elements() {
                List<Point> result = new LinkedList<Point>();
                result.addAll(Pacman.this.getWalls());
                result.addAll(Pacman.this.getHeroes());
                result.add(Pacman.this.addCasper());
                result.addAll(Pacman.this.getCookie());
                result.addAll(Pacman.this.getHollandCookie());
                
                return result;
            }
        };
    }


	public Point addCasper() {
		
		Point pos = PointImpl.pt(1,1);
		
		if (cookie.contains(pos)) {
            cookie.remove(pos);
            }
        return casper = new Casper(pos);
		
	}
	
	
}
