package model;

import logic.Game;

public abstract class Observer {
    protected Game game;
    protected UI ui;
    public abstract void update();
}
