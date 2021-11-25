import logic.Game;
import logic.ItalianGame;
import logic.SpanishGame;
import logic.TurkishGame;
import model.*;

public class CheckerGame {
    public static void main(String[] args) {
        UI ui = UI.getInstance();
        Game game = ui.gameStartup();
        game.runGame();
    }
}
