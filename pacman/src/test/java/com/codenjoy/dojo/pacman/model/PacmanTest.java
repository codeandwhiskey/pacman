package com.codenjoy.dojo.pacman.model;

import com.codenjoy.dojo.services.PrinterFactory;
import com.codenjoy.dojo.utils.TestUtils;
import com.codenjoy.dojo.pacman.services.Events;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.PrinterFactoryImpl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * User: CodeAndWhiskey
 * Date: 01.08.15
 * Time: 17:50
 */
public class PacmanTest {

    private Pacman game;
    private Hero hero;
    private Dice dice;
    private EventListener listener;
    private Player player;
    private PrinterFactory printer = new PrinterFactoryImpl();
    private Casper casper;

    @Before
    public void setup() {
        dice = mock(Dice.class);
    }

    private void dice(int...ints) {
        OngoingStubbing<Integer> when = when(dice.next(anyInt()));
        for (int i : ints) {
            when = when.thenReturn(i);
        }
    }

    private void givenFl(String board) {
        LevelImpl level = new LevelImpl(board);
        Hero hero = level.getHero().get(0);

        game = new Pacman(level, dice);
        casper = new Casper(1,1);
        listener = mock(EventListener.class);
        player = new Player(listener);
        game.newGame(player);
        player.hero = hero;
        hero.init(game);
        this.hero = game.getHeroes().get(0);
    }

    private void assertE(String expected) {
        assertEquals(TestUtils.injectN(expected),
                printer.getPrinter(game.reader(), player).print());
    }

    // есть карта со мной
    @Test
    public void shouldFieldAtStart() {
        givenFl("☼☼☼☼☼☼☼" +
				"☼.....☼" +
				"☼.....☼" +
				"☼..☺..☼" +
				"☼.....☼" +
				"☼&....☼" +
				"☼☼☼☼☼☼☼");

        assertE("☼☼☼☼☼☼☼" +
        		"☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }

    // я ходить
    @Test
    public void shouldWalkLeft() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.left();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.☺ ..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }
    
    // я ходить
    @Test
    public void shouldWalkRight() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.right();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.. ☺.☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }
     
    // я ходить
    @Test
    public void shouldWalkUp() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.up();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.. ..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }
    
    // я ходить
    @Test
    public void shouldWalkDown() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.down();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.. ..☼" +
                "☼..☺..☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }
    
    // я ходить
    @Test
    public void shouldWalkDownTwice() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.down();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.. ..☼" +
                "☼..☺..☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        
        hero.down();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.. ..☼" +
                "☼.. ..☼" +
                "☼&.☺..☼" +
                "☼☼☼☼☼☼☼");
    }

    @Test
    public void shouldWalkOnWall() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼&...☺☼" +
                "☼☼☼☼☼☼☼");

        hero.down();
        game.tick();
        
        hero.right();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼&...☺☼" +
                "☼☼☼☼☼☼☼");  
    }

    @Test
    public void shouldWalkOnWall2() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼☺....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");

        hero.up();
        game.tick();
        
        hero.left();
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼☺....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");  
    }
    //Casper add
    @Test
    public void shouldAddCasper() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
       
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }    
    //Casper move on the wall
    @Test
    public void casperShouldWalkOnWall() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        casper.move(2,1);
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
    }    

    //Casper move on right
    @Test
    public void shouldMoveCasperOnRight() {
        givenFl("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        
        casper.move(1, 2);
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼.&...☼" +
                "☼☼☼☼☼☼☼");
        
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..☺..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        
        game.tick();

        assertE("☼☼☼☼☼☼☼" +
                "☼.....☼" +
                "☼.....☼" +
                "☼..X..☼" +
                "☼.....☼" +
                "☼&....☼" +
                "☼☼☼☼☼☼☼");
        
        assertFalse(hero.isAlive());
    }
}
