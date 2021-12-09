package logic;

import model.Board;
import model.Move;
import model.PlayerColor;
import model.UI;
import model.Checker;


import java.util.ArrayList;
import java.util.List;

public class SpanishGame extends Game{
    private UI ui;
    /*
    Spanish Game:
    Same as base game, except:
        - regular checkers pieces are not allowed to capture kings.
        - kings can move and jump to any square along the diagonal (but only jump over max 1 opponent)
    */

    public SpanishGame() {
        super();
        this.ui = UI.getInstance();
    }

    public SpanishGame(PlayerContext redPlayer, PlayerContext whitePlayer, Board board){
        super(redPlayer, whitePlayer, board);
        this.ui = UI.getInstance();
    }

    public SpanishGame(SpanishGame originalGame) {
        super(originalGame);
    }

    @Override
    public Game copy(){
        return new SpanishGame(this);
    }

    @Override
    public boolean isMove(Move move, Board board){
        // Coordinates of start square must be valid
        if ((move.getFromX() < 0) || (move.getFromY() < 0) || (move.getFromX() > 7) || (move.getFromY() > 7)){return false;}
        // Coordinates of target square must be valid
        if ((move.getToX() < 0) || (move.getToY() < 0) || (move.getToX() > 7) || (move.getToY() > 7)){return false;}

        // Start square cannot be empty
        if (board.fieldIsEmpty(move.getFromX(), move.getFromY())) {return false;}
        // Target square must be empty
        if (!board.fieldIsEmpty(move.getToX(), move.getToY())) {return false;}

        if (board.fieldContainsKing(move.getFromX(), move.getFromY())) {
            return isKingMove(move, board);
        }

        if (this.isRedPlayersTurn()) {   //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(),move.getFromY(),  PlayerColor.WHITE)) {return false;}

            // if checker is pawn, nextY must be 1 larger than currentY
            int distanceY = move.getToY() - move.getFromY();
            // if checker is king, take absolute difference between nextY and currentY
            if (board.fieldContainsKing(move.getFromX(), move.getFromY())){
                distanceY = Math.abs(distanceY);
            }

            if (distanceY == 1 && (Math.abs(move.getFromX() - move.getToX()) == 1 )){return true;}

            else{return false;}
        }

        else {   //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(),PlayerColor.RED)) {return false;}

            // if checker is pawn, nextY must be 1 smaller than currentY
            int distanceY = move.getFromY() - move.getToY();
            // if checker is king, take absolute difference between nextY and currentY
            if (board.fieldContainsKing(move.getFromX(), move.getFromY())){
                distanceY = Math.abs(distanceY);
            }
            if (distanceY == 1 && (Math.abs(move.getFromX() - move.getToX()) == 1 )){
                return true;
            }
            else{return false;}
        }
    }

    private boolean isKingMove(Move move, Board board) {
        int distanceY = move.getToY() - move.getFromY();
        int distanceX = move.getToX() - move.getFromX();
        int signY = Integer.signum(distanceY);
        int signX = Integer.signum(distanceX);

        // X distance must be equal to Y distance
        if (Math.abs(distanceX) != Math.abs(distanceY)) {return false;}

        if (this.isRedPlayersTurn()) {  //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.WHITE)) {return false;}

            for (int i = 1; i < Math.abs(distanceX); i++){
                 if (!board.fieldIsEmpty(move.getFromX() + signX * i, move.getFromY() + signY * i)){
                    return false;
                }
            }

        }

        else  {  //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.RED)) {return false;}

            for (int i = 1; i < Math.abs(distanceX); i++){
                if (!board.fieldIsEmpty(move.getFromX() + signX * i, move.getFromY() + signY * i)){
                    return false;
                }
            }

        }
        return true;
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
        if (Math.abs(distanceX) != Math.abs(distanceY) || Math.abs(distanceY) < 2) {return false;}

        if (this.isRedPlayersTurn()) {  //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.getFromX(), move.getFromY(), PlayerColor.WHITE)) {return false;}

            int enemyCaptured = 0;
            for (int i = 1; i < Math.abs(distanceX); i++){
                if (board.fieldContainsCheckerColor(move.getFromX() + signX * i, move.getFromY() + signY * i, PlayerColor.WHITE)){
                    enemyCaptured += 1;
                }
                else if (board.fieldContainsCheckerColor(move.getFromX() + signX * i, move.getFromY() + signY * i, PlayerColor.RED)){
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
                if (board.fieldContainsCheckerColor(move.getFromX() + signX * i, move.getFromY() + signY * i, PlayerColor.RED)){
                    enemyCaptured += 1;
                }
                else if (board.fieldContainsCheckerColor(move.getFromX() + signX * i, move.getFromY() + signY * i, PlayerColor.WHITE)){
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


    @Override
    public boolean performJumps(Board board, List<Move> jumps){
        for (int i = 0; i < jumps.size(); i++){
            Move move = jumps.get(i);
            if (!isSingleJump(move, board)) {
                ui.printInvalidJump();
                return false;
            }
            else {
                // perform jump, update board
                Checker checker = board.removePiece(move.getFromX(), move.getFromY());
                board.addPiece(checker, move.getToX(), move.getToY());
                int distanceX = move.getToX() - move.getFromX();
                int distanceY = move.getToY() - move.getFromY();
                int signY = Integer.signum(distanceY);
                int signX = Integer.signum(distanceX);
                for (int j = 1; j < Math.abs(distanceX); j++) {
                    board.removePiece(move.getFromX() + signX * j, move.getFromY() + signY * j);
                }
            }
        }
        return true;
    }


    @Override
    public ArrayList<Move> findNextJump(List<Integer> position, Board testBoard, ArrayList<Move> previousMoves){

        for (int i = -7; i <= 7; i += 1) {
            for (int j = -7; j <= 7; j += 1) {
                Move move = new Move(position.get(0), position.get(1), position.get(0) + i, position.get(1) + j, "jump");
                if (isSingleJump(move, testBoard)) {
                    previousMoves.add(move);
                    Board boardCopy = new Board(testBoard);
                    performJumps(boardCopy, List.of(move));
                    return findNextJump(List.of(move.getToX(), move.getToY()), boardCopy, previousMoves);
                }
            }
        }
        return previousMoves;
    }


    //calculates all possible moves
    @Override
    public ArrayList<ArrayList<Move>> calcPossibleMoves(Board testBoard){
        ArrayList<List<Integer>> checkerPositions = this.findPlayerCheckers(testBoard);
        ArrayList<ArrayList<Move>> possibleMoves = new ArrayList<>();
        for (List<Integer> position : checkerPositions) {
            ArrayList<Move> nextMove = findNextJump(position, testBoard, new ArrayList<>());
            if (nextMove.size() > 0) {
                possibleMoves.add(nextMove);
            }
        }
        // if jumps are possible, return list of jumps and don't compute moves
        if (possibleMoves.size() > 0) {return possibleMoves;}

        for (List<Integer> position : checkerPositions) {
            //check if move possible
            for (int i = -7; i <= 7; i += 1) {
                for (int j = -7; j <= 7; j += 1) {
                    Move candidateMove = new Move(position.get(0), position.get(1), position.get(0) + i, position.get(1) + j, "move");
                    if (isMove(candidateMove, testBoard)){
                        ArrayList m = new ArrayList<Move>();
                        m.add(candidateMove);
                        possibleMoves.add(m);
                    }
                }
            }
        }
        return possibleMoves;
    }
}
