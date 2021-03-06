package com.codenjoy.dojo.pacman.services;

import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.pacman.client.ai.CodeAndWhiskeySolver;
import com.codenjoy.dojo.pacman.model.*;
import com.codenjoy.dojo.services.*;
import com.codenjoy.dojo.services.settings.Parameter;
import com.codenjoy.dojo.services.settings.Settings;
import com.codenjoy.dojo.services.settings.SettingsImpl;

import static com.codenjoy.dojo.services.settings.SimpleParameter.v;

/**
 * Генератор игор - реализация {@see GameType}
 * Обрати внимание на {@see GameRunner#SINGLE} - там реализовано переключение в режимы "все на одном поле"/"каждый на своем поле"
 */
public class GameRunner implements GameType {

    public final static boolean SINGLE = true;
    private final Settings settings;
    private final Level level;
    private Pacman game;
    private Casper casper;

   
    public GameRunner() {
        settings = new SettingsImpl();
        new Scores(0, settings);
        level = new LevelImpl(
                "☼☼☼☼☼☼" +
                "☼....☼" +
                "☼....☼" +
                "☼....☼" +
                "☼...#☼" +
                "☼☼☼☼☼☼");
       
    }

    private Pacman newGame() {
        return new Pacman(level, new RandomDice());
    }

    @Override
    public PlayerScores getPlayerScores(int score) {
        return new Scores(score, settings);
    }

    @Override
    public Game newGame(EventListener listener, PrinterFactory factory) {
        if (!SINGLE || game == null) {
            game = newGame();

        }

        Game game = new Single(this.game, listener, factory);
        game.newGame();
        
        return game;
    }

    @Override
    public Parameter<Integer> getBoardSize() {
        return v(level.getSize());
    }

    @Override
    public String name() {
        return "pacman";
    }

    public void newCasper(Field field) {
        Point pt = field.getFreeRandom();
        casper = new Casper(pt);
        casper.init(field);
    }

    @Override
    public Enum[] getPlots() {
        return Elements.values();
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public boolean isSingleBoard() {
        return SINGLE;
    }

    @Override
    public void newAI(String aiName) {
    	CodeAndWhiskeySolver.start(aiName, WebSocketRunner.Host.REMOTE);
    }

}
