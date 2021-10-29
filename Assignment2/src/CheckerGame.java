import logic.*;
import model.*;

import java.util.*;
import java.util.regex.*;

public class CheckerGame {

    public static Board board = new Board(true);
    public static Player redPlayer = new Player(PlayerColor.RED);
    public static Player whitePlayer = new Player(PlayerColor.WHITE);

    public static void main(String[] args) {
        UI checkerGame = new UI(true);
    }
}
