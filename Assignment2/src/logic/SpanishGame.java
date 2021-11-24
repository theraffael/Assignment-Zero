package logic;

import model.Board;
import model.Move;
import model.PlayerColor;

public class SpanishGame extends Game{
    /*
    Spanish Game:
    Same as base game, except:
        - regular checkers pieces are not allowed to capture kings.
        - kings can move and jump to any square along the diagonal
    */

    public SpanishGame() {
        super();
    }

    public SpanishGame(SpanishGame originalGame) {
        super(originalGame);
    }

    @Override
    public Game copy(){
        return new SpanishGame(this);
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

        if (board.fieldContainsKing(move.getFromX(), move.getFromY())) {
            return isKingJump(move, board);
        }

        int distanceY = move.getToY() - move.getFromY();
        int distanceX = move.getToX() - move.getFromX();
        // X distance must be 2 or -2, check Y later
        if (Math.abs(distanceX) != 2 ) {return false;}

        if (this.isRedPlayersTurn()) {  //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.WHITE)) {return false;}

            // Y distance must be 2, can be -2 if checker is king
            if (distanceY == 2 ){
                // there must be an opponents piece to jump over
                int opponentX = move.getFromX() + distanceX/2;
                int opponentY = move.getFromY() + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.WHITE)){
                    if (board.fieldContainsKing(opponentX, opponentY)){
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
            if (distanceY == -2 ){
                // there must be an opponents piece to jump over
                int opponentX = move.getFromX() + distanceX/2;
                int opponentY = move.getFromY() + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.RED)){
                    if (board.fieldContainsKing(opponentX, opponentY)){
                        return false;}
                    else {return true;}
                }
            }
        }
        // in all other cases, move is not legal
        return false;
    }

    private boolean isKingJump(Move move, Board board) {
        int distanceY = move.getToY() - move.getFromY();
        int distanceX = move.getToX() - move.getFromX();
        int signY = Integer.signum(distanceY);
        int signX = Integer.signum(distanceX);

        // X distance must be equal to Y distance
        if (Math.abs(distanceX) != Math.abs(distanceY) && Math.abs(distanceY) >= 2) {return false;}

        if (this.isRedPlayersTurn()) {  //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.WHITE)) {return false;}

            int enemyCaptured = 0;
            for (int i = 1; i < Math.abs(distanceX); i++){
                if (board.fieldContainsCheckerColor(move.getFromX() + signX * distanceX, move.getFromY() + signY * distanceY, PlayerColor.WHITE)){
                    enemyCaptured += 1;
                }
                else if (board.fieldContainsCheckerColor(move.getFromX() + signX * distanceX, move.getFromY() + signY * distanceY, PlayerColor.RED)){
                    return false;
                }
            }

            if (enemyCaptured == 1) {
                return true;
            }


        }

        else  {  //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.RED)) {return false;}

            int enemyCaptured = 0;
            for (int i = 1; i < Math.abs(distanceX); i++){
                if (board.fieldContainsCheckerColor(move.getFromX() + signX * distanceX, move.getFromY() + signY * distanceY, PlayerColor.RED)){
                    enemyCaptured += 1;
                }
                else if (board.fieldContainsCheckerColor(move.getFromX() + signX * distanceX, move.getFromY() + signY * distanceY, PlayerColor.WHITE)){
                    return false;
                }
            }

            if (enemyCaptured == 1) {
                return true;
            }

        }
        // in all other cases, move is not legal
        return false;
    }
}
