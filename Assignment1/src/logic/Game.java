package logic;
import model.Board;
import model.Player;
import model.Checker;
import model.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo: if it is a jump, then the checker of the rival player has to be removed
//todo: move has to be of length 1 (now it can move more than one field)

public class Game {
    public int getTurnCounter() {
        return turnCounter;
    }

    private int turnCounter = 0;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    public ArrayList<Coordinate> getAllPossibleMoves() {
        return allPossibleMoves;
    }

    public void setAllPossibleMoves() {
         this.allPossibleMoves = new ArrayList<Coordinate>();
    }

    private ArrayList<Coordinate> allPossibleMoves;

    public boolean isValidMove() {
        return isValidMove;
    }

    public void setValidMove(boolean validMove) {
        isValidMove = validMove;
    }

    private boolean isValidMove;

    public Game(Board board, Player redPlayer, Player whitePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }



    //checks if single move is legal
    public boolean isMove(int currentX, int currentY, int nextX, int nextY){
        // Coordinates of start square must be valid
        if ((currentX < 0) || (currentY < 0) || (currentX > 7) || (currentY > 7)){return false;}
        // Coordinates of target square must be valid
        if ((nextX < 0) || (nextY < 0) || (nextX > 7) || (nextY > 7)){return false;}

        // Start square cannot be empty
        if (board.getBoard()[currentX][currentY] == null) {return false;}
        // Target square must be empty
        if (board.getBoard()[nextX][nextY] != null) {return false;}

        if (getActivePlayer().getColor() == "R") {   //when it is red player's turn
            // Return false if checker belongs to white player
            if (board.getBoard()[currentX][currentY].getColor() == "W") {return false;}

            // if checker is pawn, nextY must be 1 larger than currentY
            int distanceY = nextY - currentY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.getBoard()[currentX][currentY].isKing()){
                distanceY = Math.abs(distanceY);
            }

            if (distanceY == 1 && (Math.abs(currentX - nextX) == 1 )){return true;}

            else{return false;}
        }

        else {   //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.getBoard()[currentX][currentY].getColor() == "R") {return false;}

            // if checker is pawn, nextY must be 1 smaller than currentY
            int distanceY = currentY - nextY;
            // if checker is king, take absolute difference between nextY and currentY
            if (board.getBoard()[currentX][currentY].isKing()){
                distanceY = Math.abs(distanceY);
            }
            if (distanceY == 1 && (Math.abs(currentX - nextX) == 1 )){
                return true;
            }
            else{return false;}
        }
    }
    //checks if a move is a jump/capture => if at least one jump on a single turn is available, at least one jump has to be taken.
    public boolean isJump(int currentX, int currentY, int nextX, int nextY){
        return true;
    }

    public boolean isSingleJump(int currentX, int currentY, int nextX, int nextY){
        // Start square cannot be empty
        if (board.getBoard()[currentX][currentY] == null) {return false;}
        // Target square must be empty
        if (board.getBoard()[nextX][nextY] != null) {return false;}

        int distanceY = nextY - currentY;
        int distanceX = nextX - currentX;
        // X distance must be 2 or -2, check Y later
        if (Math.abs(distanceX) != 2 ) {return false;}

        if (getActivePlayer().getColor() == "R") {  //when it is red player's turn
        // Return false if checker belongs to white player
            if (board.getBoard()[currentX][currentY].getColor() == "W") {return false;}

            // Y distance must be 2, can be -2 if checker is king
            if (distanceY == 2 || (distanceY == -2 && board.getBoard()[currentX][currentY].isKing())){
                // there must be an opponents piece to jump over
                int opponentX = currentX + distanceX/2;
                int opponentY = currentY + distanceY/2;
                if (board.getBoard()[opponentX][opponentY] != null && board.getBoard()[opponentX][opponentY].getColor() == "W"){
                    return true;}
            }
        }

        else { //when it is white player's turn
            // Return false if checker belongs to red player
            if (board.getBoard()[currentX][currentY].getColor() == "R") {return false;}

            // X distance must be 2 or -2
            if (Math.abs(distanceX) != 2 ) {return false;}

            // Y distance must be -2, can be 2 if checker is king
            if (distanceY == -2 || (distanceY == 2 && board.getBoard()[currentX][currentY].isKing())){
                // there must be an opponents piece to jump over
                int opponentX = currentX + distanceX/2;
                int opponentY = currentY + distanceY/2;
                if (board.getBoard()[opponentX][opponentY] != null && board.getBoard()[opponentX][opponentY].getColor() == "R"){
                    return true;}
            }
        }
        // in all other cases, move is not legal
        return false;
    }

    public static String checkIfSingleOrJump(int fromX, int fromY, int toX, int toY){
        int x = Math.abs(fromX - toX);
        if (x == 1){
            return "Single";
        }
        if (x==2){
            return "Jump";
        }
        else{
            return "";
        }
    }

    //checks whether the game is finished
    public boolean isFinished(){
        ArrayList<Checker> checkers = getActivePlayer().getCheckers();
        for (Checker checker : checkers) {
            if (!checker.isKing()) {
                //check if single move possible
                if (checker.getColor() == "W") {
                    List<Integer> moveRight = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() + 1, checker.getyPos() - 1);
                    if (moveRight.get(2) >= 0 && moveRight.get(3) >= 0 && moveRight.get(2) <= 7 && moveRight.get(3) <= 7){
                        if (isMove(moveRight)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveRight.get(2), moveRight.get(3),   "single");
                        }
                    }
                    List<Integer> moveLeft = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() - 1, checker.getyPos() - 1);
                    if (moveLeft.get(2) >= 0 && moveLeft.get(3) >= 0 && moveLeft.get(2) <= 7 && moveLeft.get(3) <= 7){
                        if (isMove(moveLeft)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveLeft.get(2), moveLeft.get(3),  "single");
                        }
                    }

                } else {
                    List<Integer> moveRight = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() + 1, checker.getyPos() + 1);
                    if (moveRight.get(2) >= 0 && moveRight.get(3) >= 0 && moveRight.get(2) <= 7 && moveRight.get(3) <= 7){
                        if (isMove(moveRight)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(), moveRight.get(2), moveRight.get(3),  "single");
                        }
                    }
                    List<Integer> moveLeft = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() - 1, checker.getyPos() + 1);
                    if (moveLeft.get(2) >= 0 && moveLeft.get(3) >= 0 && moveLeft.get(2) <= 7 && moveLeft.get(3) <= 7){
                        if (isMove(moveLeft)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveLeft.get(2), moveLeft.get(3),  "single");
                        }
                    }
                }

                //check if jump possible
                if (checker.getColor() == "W") {
                    List<Integer> moveRight = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() + 2, checker.getyPos() - 2);
                    if (moveRight.get(2) >= 0 && moveRight.get(3) >= 0 && moveRight.get(2) <= 7 && moveRight.get(3) <= 7){
                        if (isSingleJump(moveRight)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveRight.get(2), moveRight.get(3), "jump");
                        }
                    }
                    List<Integer> moveLeft = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() - 2, checker.getyPos() - 2);
                    if (moveLeft.get(2) >= 0 && moveLeft.get(3) >= 0 && moveLeft.get(2) <= 7 && moveLeft.get(3) <= 7){
                        if (isSingleJump(moveLeft)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveLeft.get(2), moveLeft.get(3), "jump");
                        }
                    }
                } else {
                    List<Integer> moveRight = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() + 2, checker.getyPos() + 2);
                    if (moveRight.get(2) >= 0 && moveRight.get(3) >= 0 && moveRight.get(2) <= 7 && moveRight.get(3) <= 7){
                        if (isSingleJump(moveRight)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveRight.get(2), moveRight.get(3), "jump");
                        }
                    }
                    List<Integer> moveLeft = Arrays.asList(checker.getxPos(), checker.getyPos(), checker.getxPos() - 2, checker.getyPos() + 2);
                    if (moveLeft.get(2) >= 0 && moveLeft.get(3) >= 0 && moveLeft.get(2) <= 7 && moveLeft.get(3) <= 7){
                        if (isSingleJump(moveLeft)) {
                            checker.setPossibleMoves(checker.getxPos(), checker.getyPos(),moveLeft.get(2), moveLeft.get(3), "jump");
                        }
                    }
                }
            }
        }
        this.setAllPossibleMoves();
        for(Checker checker : checkers){
            this.allPossibleMoves.addAll(checker.getPossibleMoves());
        }
        if (this.allPossibleMoves.isEmpty()){
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
    public void CalcPossibleMoves(){
        ArrayList checkers = getActivePlayer().getCheckers();
        for (int i = 0; i<checkers.size(); i++){
            Checker c = (Checker)checkers.get(i);
        }
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public void increaseTurnCounter() {
        this.turnCounter++;
    }

    public void newMove(ArrayList<List<Integer>> convertedMoves) {
        Board testBoard = new Board(board.getBoard());

        if (convertedMoves.size() == 1){
            List<Integer> move = convertedMoves.get(0);
            int fromX = move.get(0);
            int fromY = move.get(1);
            int toX = move.get(2);
            int toY = move.get(3);

            if (isMove(move)){
                isValidMove = true;
                // todo: check if no jump is possible anywhere
                for(Coordinate coordinate : this.allPossibleMoves){
                    if(coordinate.getS() == "jump"){
                        isValidMove = false;
                        System.out.println("Invalid move, there is a mandatory jump available");
                        break;
                    }
                }
                if (isValidMove){
                    // perform move
                    Checker checker = testBoard.removePiece(fromX, fromY);
                    isCrown(checker, toY);
                    testBoard.addPiece(checker, toX, toY);
                }

            }
            else if (isSingleJump(move)){
                // perform single jump
                isValidMove = true;
                Checker checker = testBoard.removePiece(fromX, fromY);

                isCrown(checker, toY);

                testBoard.addPiece(checker, toX, toY);

                int distanceX = toX - fromX;
                int distanceY = toY - fromY;

                Checker capturedChecker = testBoard.removePiece(fromX + distanceX/2, fromY + distanceY/2);

                capturedChecker.capture();

                this.isFinished();
                for(Coordinate coordinate : this.allPossibleMoves){
                    if(toX == coordinate.getX() && toY == coordinate.getY() && coordinate.getS() == "jump"){
                        isValidMove = false;
                        checker = testBoard.removePiece(toX, toY);
                        testBoard.addPiece(checker, fromX, fromY);
                        turnCounter--;
                        turnCounter--;
                        System.out.println("Invalid move, there is a mandatory double jump available");
                        break;
                    }
                }


                // todo: check if no more jump is possible
            }
            else{
                isValidMove = false;
            }

        }

        else {
            for (int i = 0; i < convertedMoves.size(); i++){
                List<Integer> move = convertedMoves.get(i);
                int fromX = move.get(0);
                int fromY = move.get(1);
                int toX = move.get(2);
                int toY = move.get(3);
                if (!isSingleJump(move)) {
                    isValidMove = false;
                    break;}
                else{
                    isValidMove = true;
                    // perform jump, update testboard
                    Checker checker = testBoard.removePiece(fromX, fromY);
                    isCrown(checker, toY);
                    testBoard.addPiece(checker, toX, toY);
                    int distanceX = toX - fromX;
                    int distanceY = toY - fromY;
                    Checker capturedChecker = testBoard.removePiece(fromX + distanceX/2, fromY + distanceY/2);
                    capturedChecker.capture();
                }
            }
        }
        if (isValidMove){
            turnCounter++;
            board = testBoard;
            board.display();
            System.out.println("Player Turn: " + this.getActivePlayer().getColorWord());
        }


    }

    private void isCrown(Checker checker, int toY){

        String playerColor = checker.getColor();
        if (playerColor == "W"){
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
    private boolean isSingleJump(List<Integer> integers) {
        return isSingleJump(integers.get(0), integers.get(1), integers.get(2), integers.get(3));
    }

    private boolean isMove(List<Integer> integers) {
        return isMove(integers.get(0), integers.get(1), integers.get(2), integers.get(3));
    }
}
