package logic;

import model.*;


import java.util.ArrayList;
import java.util.List;

public class Game {
    private int turnCounter = 0;
    private Board board;
    private PlayerContext redPlayer;
    private PlayerContext whitePlayer;
    private UI ui;
    private boolean isFinished;
    private ArrayList possibleMoves;

    public Game(Board board, PlayerContext redPlayer, PlayerContext whitePlayer, UI ui) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.ui = ui;
    }

    public Game(Game originalGame){
        this.board = new Board(originalGame.board);
        this.turnCounter = originalGame.turnCounter;
    }

    public Game copy(){
        return new Game(this);
    }

    public void runGame(){
        isFinished = false;
        while (!this.isFinished()){
            ArrayList<Move> convertedMoves;
            boolean moveSuccessful = false;

             //print out who's turn it is
            if(isRedPlayersTurn()){
                System.out.println(ui.returnPlayerTurn(true));
            }
            else{
                System.out.println(ui.returnPlayerTurn(false));
            }

            while(!moveSuccessful){
                    if(isRedPlayersTurn()){
                        convertedMoves = redPlayer.getMove(copy(), ui);
                        }
                    else{
                        convertedMoves = whitePlayer.getMove(copy(), ui);
                    }
                    moveSuccessful = this.newMove(convertedMoves);

                }
            ui.display();
            }
        //display reason for game over
        if(this.findPlayerCheckers(board).size() == 0){
            ui.gameFinishedNoMorePieces(getActivePlayer().toString());
        }
        else if (possibleMoves.isEmpty()){
            ui.gameFinishedNoMoreMoves(getActivePlayer().toString());
        }

        //display moves the game took
        ui.displayAmountOfMoves(turnCounter);
    }


    //checks if single move is legal
    public boolean isMove(Move move, Board board){
        // Coordinates of start square must be valid
        if ((move.getFromX() < 0) || (move.getFromY() < 0) || (move.getFromX() > 7) || (move.getFromY() > 7)){return false;}
        // Coordinates of target square must be valid
        if ((move.getToX() < 0) || (move.getToY() < 0) || (move.getToX() > 7) || (move.getToY() > 7)){return false;}

        // Start square cannot be empty
        if (board.fieldIsEmpty(move.getFromX(), move.getFromY())) {return false;}
        // Target square must be empty
        if (!board.fieldIsEmpty(move.getToX(), move.getToY())) {return false;}

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
                    return true;}
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
                    return true;}
            }
        }
        // in all other cases, move is not legal
        return false;
    }


    //checks whether the game is finished
    public boolean isFinished(){

        this.possibleMoves = calcPossibleMoves(board);

        if(this.findPlayerCheckers(board).size() == 0){
            return true;
        }
        if (possibleMoves.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList findPlayerCheckers(Board board) {
        ArrayList checkers = new ArrayList();
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                if (board.fieldContainsCheckerColor(x, y, this.getActivePlayer())) {
                    checkers.add(List.of(x,y));
                }
            }
        }
        return checkers;
    }

    public ArrayList findPlayerCheckers(PlayerColor color) {
        ArrayList checkers = new ArrayList();
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                if (board.fieldContainsCheckerColor(x, y, color)) {
                    checkers.add(List.of(x,y));
                }
            }
        }
        return checkers;
    }

    //returns the Player whose turn it is
    public PlayerColor getActivePlayer(){
        if (turnCounter % 2 == 0){
            return PlayerColor.RED;
        }
        else {
            return PlayerColor.WHITE;
        }
    }

    private ArrayList<Move> findNextJump(List<Integer> position, Board testBoard, ArrayList<Move> previousMoves){

        for (int i = -2; i <= 2; i += 4) {
            for (int j = -2; j <= 2; j += 4) {
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

    public ArrayList<ArrayList<Move>> calcPossibleMoves(){
        return calcPossibleMoves(new Board(this.board));
    }

    //calculates all possible moves
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
            for (int i = -1; i<=1; i+=2){
                for (int j = -1; j<=1; j+=2){
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


    private boolean performJumps(Board board, List<Move> jumps){
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
                board.removePiece(move.getFromX() + distanceX/2, move.getFromY() + distanceY/2);
            }
        }
        return true;
    }

    public boolean newMove(ArrayList<Move> convertedMoves) {
        if (convertedMoves.isEmpty()) {return false;}
        Move firstMove = convertedMoves.get(0);
        // handle simple move inputs
        if (convertedMoves.size() == 1 && isMove(firstMove, board)){
            // check if no jump is possible anywhere
            for(ArrayList<Move> possibleMove : calcPossibleMoves(board)){
                if(possibleMove.get(0).isMoveJump())
                {
                    ui.printInvalidMoveMandatoryJump();
                    return false;
                }
            }
            // perform move
            Checker checker = board.removePiece(firstMove.getFromX(), firstMove.getFromY());
            board.addPiece(checker, firstMove.getToX(), firstMove.getToY());

        }

        // handle jumps and multi jumps inputs
        else {
            Board boardCopy =  new Board(this.board);
            boolean isValid = performJumps(boardCopy, convertedMoves);
            if (!isValid) {return false;}
            // test if the checker we just moved could make another jump
            Move move = convertedMoves.get(convertedMoves.size() -1 );
            ArrayList nextJump = findNextJump(List.of(move.getToX(), move.getToY()), boardCopy, new ArrayList<>());
            if (nextJump.size() > 0) {
                ui.printInvalidMoveFurtherJump();
                return false;
            }
            else{
                performJumps(this.board, convertedMoves);
            }
        }

        board.checkAndCrown();
        turnCounter++;
        return true;
    }

    public void pass(){
        turnCounter++;
    }

    public boolean isRedPlayersTurn() {
        if(this.getActivePlayer() == PlayerColor.RED){
            return true;
        }
        else{
            return false;
        }
    }

}
