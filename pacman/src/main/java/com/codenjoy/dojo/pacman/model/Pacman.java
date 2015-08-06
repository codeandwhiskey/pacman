package com.codenjoy.dojo.pacman.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.codenjoy.dojo.pacman.services.Events;
import com.codenjoy.dojo.services.BoardReader;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.Tickable;

/**
 * О! Это самое сердце игры - борда, на которой все происходит.
 * Если какой-то из жителей борды вдруг захочет узнать что-то у нее, то лучше ему дать интефейс {@see Field}
 * Борда реализует интерфейс {@see Tickable} чтобы быть уведомленной о каждом тике игры. Обрати внимание на {Sample#tick()}
 */
public class Pacman implements Tickable, Field {
	private List<Wall> walls;
    private List<Cookie> cookie;
    private List<Bomb> bombs;
    Casper casper;

    private List<Player> players;

    private final int size;
    private Dice dice;

    public Pacman(Level level, Dice dice) {
        this.dice = dice;
        walls = level.getWalls();
        cookie = level.getCookie();
        size = level.getSize();
        players = new LinkedList<Player>();
        casper =  new Casper(getFreeRandom());
        
    }

    /**
     * @see Tickable#tick()
     */
    @Override
    public void tick() {
    	
        for (Player player : players) {
            Hero hero = player.getHero();

            hero.tick();
            
            if (casper.getX() == hero.getX() && casper.getY() == hero.getY()) {
            	hero.setAlive(false);
            	
               }
            
            if (cookie.contains(hero)) {
            	cookie.remove(hero);
                player.event(Events.WIN);

            }
            
        }
        
       
      /*  if (!casper.isAlive()) {
     	Point pos = getFreeRandom();

        	casper = new Casper(pos);
        	
        }*/
        
    

        for (Player player : players) {
            Hero hero = player.getHero();
           
            if (!hero.isAlive()) {
                player.event(Events.LOOSE);
            }
        }
        
        casper.tick();
    }


	public int size() {
        return size;
    }

    @Override
    public boolean isBarrier(int x, int y) {
        Point pt = PointImpl.pt(x, y);
        System.out.println(x > size - 1 || x < 0 || y < 0 || y > size - 1 || walls.contains(pt) || getHeroes().contains(pt));
        return x > size - 1 || x < 0 || y < 0 || y > size - 1 || walls.contains(pt) || getHeroes().contains(pt) ;
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

        return 
                !walls.contains(pt) &&
                !getHeroes().contains(pt);
    }


    public List<Cookie> getCookie() {
        return cookie;
    }

    public List<Hero> getHeroes() {
        List<Hero> result = new ArrayList<Hero>(players.size());
        for (Player player : players) {
            result.add(player.getHero());
        }
        return result;
    }

    public void newGame(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
        player.newHero(this);
        player.newCasper(this);
        
       
        
    }

   public Casper getCasper() {
      
       return this.casper;
	}

	public void remove(Player player) {
        players.remove(player);
    }

    public List<Wall> getWalls() {
        return walls;
    }
   

	public List<Bomb> getBombs() {
        return bombs;
    }

    public BoardReader reader() {
        return new BoardReader() {
            private int size = this.size;

            @Override
            public int size() {
                return Pacman.this.size;
            }

            @Override
            public Iterable<? extends Point> elements() {
                List<Point> result = new LinkedList<Point>();
                result.addAll(Pacman.this.getWalls());
                result.add(getCasper());
                result.addAll(Pacman.this.getHeroes());
                result.addAll(Pacman.this.getCookie());
                return result;
            }
        };
    }
}
