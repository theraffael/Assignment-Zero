package logic;

import model.*;
import logic.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MinMaxPlayerTest {
    private static Game game;
    private static Board board;

    @BeforeClass
    public static void beforeClass(){}

    @Before
    public void before(){
        board = new Board(false);
        PlayerContext redPlayer = new PlayerContext(new HumanPlayer(), PlayerColor.RED);
        PlayerContext whitePlayer = new PlayerContext(new HumanPlayer(), PlayerColor.WHITE);

        /*      Setup board for testing purposes:
                     0      1      2      3      4      5      6      7
                     a      b      c      d      e      f      g      h
                  +_______________________________________________________+

            0   1 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]

            1   2 |  [   ]  [   ]  [   ]  [   ]  [   ]  [   ]  [W_K]  [   ]

            2   3 |  [   ]  [   ]  [   ]  [R_P]  [   ]  [R_P]  [   ]  [   ]

            3   4 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [   ]  [   ]

            4   5 |  [   ]  [   ]  [   ]  [   ]  [   ]  [R_P]  [   ]  [   ]

            5   6 |  [   ]  [   ]  [W_P]  [   ]  [   ]  [   ]  [W_P]  [   ]

            6   7 |  [   ]  [   ]  [   ]  [   ]  [   ]  [W_P]  [   ]  [   ]

            7   8 |  [   ]  [   ]  [R_P]  [   ]  [   ]  [   ]  [   ]  [   ]
        */
        Checker king = new Checker(PlayerColor.WHITE);
        king.crown();
        board.addPiece(king, 6,1);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,3);
        board.addPiece(new Checker(PlayerColor.WHITE), 2,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 6,5);
        board.addPiece(new Checker(PlayerColor.WHITE), 5,6);

        board.addPiece(new Checker(PlayerColor.RED), 3,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,2);
        board.addPiece(new Checker(PlayerColor.RED), 5,4);
        board.addPiece(new Checker(PlayerColor.RED), 2,7);

        game = new Game();
    }

    @Test
    public void testGetMove(){

    }

    @Test
    public void testGetValue(){

    }


    @After
    public void after(){

    }

    @AfterClass
    public static void afterClass(){

    }

}
