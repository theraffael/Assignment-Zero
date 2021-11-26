package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;

public class ItalianGame extends Game{
    private UI ui;
    /*
    Italian Game:
    Same as base game, except:
        - regular checkers pieces are not allowed to capture kings.
    */

    public ItalianGame() {
        super();
        this.ui = UI.getInstance();
    }

    public ItalianGame(PlayerContext redPlayer, PlayerContext whitePlayer, Board board) {
        super(redPlayer, whitePlayer, board);
        this.ui = UI.getInstance();
    }

    public ItalianGame(ItalianGame originalGame) {
        super(originalGame);
    }

    @Override
    public Game copy(){
        return new ItalianGame(this);
    }

    @Override
    public boolean isSingleJump(Move move, Board board){
        // Coordinates of start square must be valid
        if ((move.getFromX() < 0) || (move.getFromY() < 0) || (move.getFromX() > 7) || (move.getFromY() > 7)){return false;}
        // Coordinates of target square must be valid
        if ((move.getToX() < 0) || (move.getToY() < 0) || (move.getToX() > 7) || (move.getToY() > 7)){return false;}
        // Start square cannot be empty
        if (board.fieldIsEmpty(move.getFromX(), move.getFromY())) {return false;}
        // Target square must be empty
        if (!board.fieldIsEmpty(move.getToX(), move.getToY())) {return false;}

        int distanceY = move.getToY() - move.getFromY();
        int distanceX = move.getToX() - move.getFromX();
        // X distance must be 2 or -2, check Y later
        if (Math.abs(distanceX) != 2 ) {return false;}

        if (this.isRedPlayersTurn()) {  //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.WHITE)) {return false;}

            // Y distance must be 2, can be -2 if checker is king
            if (distanceY == 2 || (distanceY == -2 && board.fieldContainsKing(move.getFromX(), move.getFromY()))){
                // there must be an opponents piece to jump over
                int opponentX = move.getFromX() + distanceX/2;
                int opponentY = move.getFromY() + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.WHITE)){
                    if (board.fieldContainsKing(opponentX, opponentY) && !board.fieldContainsKing(move.getFromX(), move.getFromY())){
                    return false;}
                    else {return true;}

                }
            }
        }

        else { //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.RED)) {return false;}

            // X distance must be 2 or -2
            if (Math.abs(distanceX) != 2 ) {return false;}

            // Y distance must be -2, can be 2 if checker is king
            if (distanceY == -2 || (distanceY == 2 && board.fieldContainsKing(move.getFromX(), move.getFromY()))){
                // there must be an opponents piece to jump over
                int opponentX = move.getFromX() + distanceX/2;
                int opponentY = move.getFromY() + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.RED)){
                    if (board.fieldContainsKing(opponentX, opponentY) && !board.fieldContainsKing(move.getFromX(), move.getFromY())){
                        return false;}
                    else {return true;}
                }
            }
        }
        // in all other cases, move is not legal
        return false;
    }
}
