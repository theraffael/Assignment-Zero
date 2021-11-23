package model;

import logic.Game;

public class UIUpdateObserver extends Observer{
    public UIUpdateObserver(Game game, UI ui){
        this.game = game;
        this.ui = ui;
        this.game.attach(this);
    }

    @Override
    public void update() {
        ui.display(game.getState());
    }
}
