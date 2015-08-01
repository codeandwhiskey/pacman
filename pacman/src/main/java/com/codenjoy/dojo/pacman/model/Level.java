package com.codenjoy.dojo.pacman.model;

import java.util.List;

/**
 * Я вот для простоты и удобства хочу указывать борду в тестовом виде, а реализация этого интерфейса позволяет мне это сделать
 */
public interface Level {

    /**
     * @return Размер поля (обязательно квадратное)
     */
    int getSize();

    List<Wall> getWalls();

    List<Hero> getHero();

    List<Cookie> getCookie();
}
