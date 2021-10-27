package logic;

import model.*;

import com.sun.tools.javac.util.List;
import java.util.ArrayList;

public class Game {
    private int turnCounter = 0;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    public Game(Board board, Player redPlayer, Player whitePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }

    //checks if single move is legal
    public boolean isMove(Move move, Board board){
        // Coordinates of start square must be valid
        if ((move.fromX < 0) || (move.fromY < 0) || (move.fromX > 7) || (move.fromY > 7)){return false;}
        // Coordinates of target square must be valid
        if ((move.toX < 0) || (move.toY < 0) || (move.toX > 7) || (move.toY > 7)){return false;}

        // Start square cannot be empty
        if (board.fieldIsEmpty(move.fromX, move.fromY)) {return false;}
        // Target square must be empty
        if (!board.fieldIsEmpty(move.toX, move.toY)) {return false;}

        if (getActivePlayer().isRedPlayersTurn()) {   //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.fromX,move.fromY,  PlayerColor.WHITE)) {return false;}

            // if checker is pawn, nextY must be 1 larger than currentY
            int distanceY = move.toY - move.fromY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.fieldContainsKing(move.fromX, move.fromY)){
                distanceY = Math.abs(distanceY);
            }

            if (distanceY == 1 && (Math.abs(move.fromX - move.toX) == 1 )){return true;}

            else{return false;}
        }

        else {   //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.fromX, move.fromY,PlayerColor.RED)) {return false;}

            // if checker is pawn, nextY must be 1 smaller than currentY
            int distanceY = move.fromY - move.toY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.fieldContainsKing(move.fromX, move.fromY)){
                distanceY = Math.abs(distanceY);
            }
            if (distanceY == 1 && (Math.abs(move.fromX - move.toX) == 1 )){
                return true;
            }
            else{return false;}
        }
    }

    public boolean isSingleJump(Move move, Board board){
        // Coordinates of start square must be valid
        if ((move.fromX < 0) || (move.fromY < 0) || (move.fromX > 7) || (move.fromY > 7)){return false;}
        // Coordinates of target square must be valid
        if ((move.toX < 0) || (move.toY < 0) || (move.toX > 7) || (move.toY > 7)){return false;}
        // Start square cannot be empty
        if (board.fieldIsEmpty(move.fromX, move.fromY)) {return false;}
        // Target square must be empty
        if (!board.fieldIsEmpty(move.toX, move.toY)) {return false;}

        int distanceY = move.toY - move.fromY;
        int distanceX = move.toX - move.fromX;
        // X distance must be 2 or -2, check Y later
        if (Math.abs(distanceX) != 2 ) {return false;}

        if (getActivePlayer().isRedPlayersTurn()) {  //when it is red player's turn
        // Return false if checker belongs to white player
            if (board.fieldContainsCheckerColor(move.fromX, move.fromY, PlayerColor.WHITE)) {return false;}

            // Y distance must be 2, can be -2 if checker is king
            if (distanceY == 2 || (distanceY == -2 && board.fieldContainsKing(move.fromX, move.fromY))){
                // there must be an opponents piece to jump over
                int opponentX = move.fromX + distanceX/2;
                int opponentY = move.fromY + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.WHITE)){
                    return true;}
            }
        }

        else { //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.fieldContainsCheckerColor(move.fromX, move.fromY, PlayerColor.RED)) {return false;}

            // X distance must be 2 or -2
            if (Math.abs(distanceX) != 2 ) {return false;}

            // Y distance must be -2, can be 2 if checker is king
            if (distanceY == -2 || (distanceY == 2 && board.fieldContainsKing(move.fromX, move.fromY))){
                // there must be an opponents piece to jump over
                int opponentX = move.fromX + distanceX/2;
                int opponentY = move.fromY + distanceY/2;
                if (board.fieldContainsCheckerColor(opponentX, opponentY, PlayerColor.RED)){
                    return true;}
            }
        }
        // in all other cases, move is not legal
        return false;
    }


    //checks whether the game is finished
    public boolean isFinished(){

        ArrayList possibleMoves = calcPossibleMoves(board);

        if(getActivePlayer().findPlayerCheckers(board).size() == 0){
            System.out.println(getActivePlayer().playerColorToString() + " has no more pieces left and loses this game");
            return true;
        }
        if (possibleMoves.isEmpty()){
            System.out.println(getActivePlayer().playerColorToString() + " has no more possible moves left and loses this game");
            return true;
        }
        else{
            return false;
        }
    }

    //returns the Player whose turn it is
    public Player getActivePlayer(){
        if (turnCounter % 2 == 0){
            return redPlayer;
        }
        else {
            return whitePlayer;
        }
    }

    //calculates all possible moves
    public ArrayList<Move> calcPossibleMoves(Board testBoard){
        ArrayList<List<Integer>> checkerPositions = getActivePlayer().findPlayerCheckers(testBoard);
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for (List<Integer> position : checkerPositions) {
            //check if jump possible
            for (int i = -2; i <= 2; i += 4) {
                for (int j = -2; j <= 2; j += 4) {
                    Move candidateMove = new Move(position.get(0), position.get(1), position.get(0) + i, position.get(1) + j, "jump");
                    if (isSingleJump(candidateMove, testBoard)) {
                        possibleMoves.add(candidateMove);
                    }
                }
            }
        }
        // if jumps are possible, return list of jumps and don't compute moves
        if (possibleMoves.size() > 0) {return possibleMoves;}

        for (List<Integer> position : checkerPositions) {
            //check if move possible
            for (int i = -1; i<=1; i+=2){
                for (int j = -1; j<=1; j+=2){
                    Move candidateMove = new Move(position.get(0), position.get(1), position.get(0) + i, position.get(1) + j, "move");
                    if (isMove(candidateMove, testBoard)){
                        possibleMoves.add(candidateMove);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public void increaseTurnCounter() {
        this.turnCounter++;
    }

    public boolean newMove(ArrayList<Move> convertedMoves) {
        Board testBoard =  new Board(this.board);

        // handle one move inputs
        if (convertedMoves.size() == 1){
            Move move = convertedMoves.get(0);
            if (isMove(move, testBoard)){
                // check if no jump is possible anywhere
                for(Move possibleMove : calcPossibleMoves(testBoard)){
                    if(possibleMove.isMoveJump())
                    {
                        System.out.println("Invalid move, there is a mandatory jump available");
                        return false;
                    }
                }
                // perform move
                Checker checker = testBoard.removePiece(move.fromX, move.fromY);
                isCrown(checker, move.toY);
                testBoard.addPiece(checker, move.toX, move.toY);

            }
            else if (isSingleJump(move, testBoard)){
                // perform single jump
                Checker checker = testBoard.removePiece(move.fromX, move.fromY);
                isCrown(checker, move.toY);
                testBoard.addPiece(checker, move.toX, move.toY);

                int distanceX = move.toX - move.fromX;
                int distanceY = move.toY - move.fromY;

                testBoard.removePiece(move.fromX + distanceX/2, move.fromY + distanceY/2);

                // test if the checker we just moved could make another jump
                for(Move possibleMove : calcPossibleMoves(testBoard)){
                    if(move.toX == possibleMove.fromX && move.toY == possibleMove.fromY && possibleMove.isMoveJump()){
                        System.out.println("Invalid move, the checker can jump further");
                        return false;
                    }
                }
            }
            else{
                System.out.println("Invalid move, please try again.");
                return false;
            }

        }

        // handle multi move inputs
        else {
            for (int i = 0; i < convertedMoves.size(); i++){
                Move move = convertedMoves.get(i);
                if (!isSingleJump(move, testBoard)) {
                    System.out.println("Invalid jump, please try again.");
                    return false;
                }
                else {
                    // perform jump, update testboard
                    Checker checker = testBoard.removePiece(move.fromX, move.fromY);
                    isCrown(checker, move.toY);
                    testBoard.addPiece(checker, move.toX, move.toY);
                    int distanceX = move.toX - move.fromX;
                    int distanceY = move.toY - move.fromY;
                    testBoard.removePiece(move.fromX + distanceX/2, move.fromY + distanceY/2);
                }
            }
            // test if the checker we just moved could make another jump
            Move move = convertedMoves.get(convertedMoves.size() -1 );
            for(Move possibleMove : calcPossibleMoves(testBoard)){
                if(move.toX == possibleMove.fromX && move.toY == possibleMove.fromY && possibleMove.isMoveJump()){
                    System.out.println("Invalid move, the checker can jump further");
                    return false;
                }
            }
        }

        turnCounter++;
        this.board = testBoard;
        this.board.display();
        System.out.println("Player Turn: " + this.getActivePlayer().playerColorToString());
        return true;
    }

    private void isCrown(Checker checker, int toY){

        if (checker.isWhitePlayerChecker()){
            if (toY == 0){
                checker.crown();
            }
        }
        else{
            if (toY == 7){
                checker.crown();
            }
        }

    }
}
