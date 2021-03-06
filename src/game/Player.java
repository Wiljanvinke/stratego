package game;

import game.exceptions.*;
import game.extra.Color;
import game.extra.Rank;
import game.pieces.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private Color color;
    private ArrayList<Piece> pieces;
    private Piece[][] graveyard;
    private Board board;


    public Player(Color color, Board board) {
        this.color = color;
        this.board = board;
        pieces = new ArrayList<Piece>();
        constructGraveyard();

        Piece newPiece = new Flag(this);
        pieces.add(newPiece);
        newPiece = new Spy(this);
        pieces.add(newPiece);

        //Making Scouts
        for (int i = 0; i < 8; i++) {
            newPiece = new Scout(this);
            pieces.add(newPiece);
        }
        //Making Miners
        for (int i = 0; i < 5; i++) {
            newPiece = new Miner(this);
            pieces.add(newPiece);
        }
        //Making Sergeants
        for (int i = 0; i < 4; i++) {
            newPiece = new Piece(this, 4);
            pieces.add(newPiece);
        }
        //Making Lieutenants
        for (int i = 0; i < 4; i++) {
            newPiece = new Piece(this, 5);
            pieces.add(newPiece);
        }
        //Making Captains
        for (int i = 0; i < 4; i++) {
            newPiece = new Piece(this, 6);
            pieces.add(newPiece);
        }
        //Making Majors
        for (int i = 0; i < 3; i++) {
            newPiece = new Piece(this, 7);
            pieces.add(newPiece);
        }
        //Making Colonels
        for (int i = 0; i < 2; i++) {
            newPiece = new Piece(this, 8);
            pieces.add(newPiece);
        }

        pieces.add(new Piece(this, 9));
        pieces.add(new Piece(this, 10));

        //Making Bombs
        for (int i = 0; i < 6; i++) {
            newPiece = new Bomb(this);
            pieces.add(newPiece);
        }

        //printPieces();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece[][] getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(Piece[][] graveyard) {
        this.graveyard = graveyard;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void constructGraveyard(){
        graveyard = new Piece[][]{new Piece[1], new Piece[1], new Piece[8], new Piece[5], new Piece[4], new Piece[4],
                new Piece[4], new Piece[3], new Piece[2], new Piece[1], new Piece[1], new Piece[6]};
    }

    //TODO move simulation (ex. bombs on the first row)
    public boolean canMakeMove(){
        for(Piece piece: pieces){
            if(piece.getRange() > 0 ){
                return true;
            }
        }
        return false;
    }

    public boolean hasFlag(){
        if (graveyard[0][0] == null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move a Piece from one Field to another using user input
     *
     * @throws InvalidMoveException if the entered move was invalid
     */
    public void makeMove() throws InvalidMoveException {
        //Select a field your piece is on
        System.out.println("> " + this.color + ", what row is the piece in (0-9)?");
        int row = chooseCoordinate();
        System.out.println("> " + this.color + ", what column is the piece in (0-9)?");
        int col = chooseCoordinate();
        Field ownField = board.getPlayFields()[row][col];

        //Check if Field has a Piece on it
        if (ownField.isOccupied()) {
            Piece piece = ownField.getPiece();
            System.out.println("Row: " + row + ", Column: " + col + ", Piece: " + piece.toString() + ", Range: " + piece.getRange());

            //Check if Piece belongs to this Player
            if (piece.getPlayer() == this) {
                //Select the field you want to move to
                System.out.println("> " + this.color + ", what row do you want to move the piece to (0-9)?");
                int dRow = chooseCoordinate();
                System.out.println("> " + this.color + ", what column do you want to move the piece to (0-9)?");
                int dCol = chooseCoordinate();
                Field destination = board.getPlayFields()[dRow][dCol];

                //if around checkValidMove not necessary
                if (checkValidMove(row, col, dRow, dCol)) {
                    checkValidSteps(row, col, dRow, dCol);
                }
                if (!destination.isOccupied()) {
                    destination.setPiece(piece);
                    ownField.setPiece(null);
                } else {
                    //Check if the Piece on the destination Field belongs to this Player
                    if (destination.getPiece().getPlayer() != this) {
                        attack(ownField, destination);
                    } else {
                        throw new OccupiedFieldException(this);
                    }
                }

            } else {
                throw new InvalidPieceException(this);
            }
        } else {
            throw new SelectEmptyException(this);
        }

    }

    public void checkValidSteps(int row, int col, int dRow, int dCol) throws InvalidMoveException {
        int moveRange = -1;
        String direction = null;
        if (row < dRow) {
            direction = "down";
            moveRange = Math.abs(row - dRow);
        }
        if (row > dRow) {
            direction = "up";
            moveRange = Math.abs(row - dRow);
        }
        if (col < dCol) {
            direction = "right";
            moveRange = Math.abs(col - dCol);
        }
        if (col > dCol) {
            direction = "left";
            moveRange = Math.abs(col - dCol);
        }
        for (int i = 0; i < moveRange; i++) {
            Field tempOwn = board.getPlayFields()[row][col];
            switch (direction) {
                case "down":
                    if (checkValidMove(row, col, row + 1, dCol)) {
                        Field tempDestination = board.getPlayFields()[row + 1][col];
                        if (tempOwn.isOccupied() && tempDestination.isOccupied() &&
                                tempOwn.getPiece().getPlayer().equals(tempDestination.getPiece().getPlayer())) {
                            throw new InvalidStepException("Another Piece blocks the path");
                        }
                        row++;
                        break;
                    } else {
                        throw new InvalidStepException("Invalid step in range");
                    }
                case "up":
                    if (checkValidMove(row, col, row -1, dCol)) {
                        Field tempDestination = board.getPlayFields()[row - 1][col];
                        if (tempOwn.isOccupied() && tempDestination.isOccupied() &&
                                tempOwn.getPiece().getPlayer().equals(tempDestination.getPiece().getPlayer())) {
                            throw new InvalidStepException("Another Piece blocks the path");
                        }
                        row--;
                        break;
                    } else {
                        throw new InvalidStepException("Invalid step in range");
                    }
                case "right":
                    if (checkValidMove(row, col, dRow, col + 1)) {
                        Field tempDestination = board.getPlayFields()[row][col + 1];
                        if (tempOwn.isOccupied() && tempDestination.isOccupied() &&
                                tempOwn.getPiece().getPlayer().equals(tempDestination.getPiece().getPlayer())) {
                            throw new InvalidStepException("Another Piece blocks the path");
                        }
                        col++;
                        break;
                    } else {
                        throw new InvalidStepException("Invalid step in range");
                    }
                case "left":
                    if (checkValidMove(row, col, dRow, col - 1)) {
                        Field tempDestination = board.getPlayFields()[row][col - 1];
                        if (tempOwn.isOccupied() && tempDestination.isOccupied() &&
                                tempOwn.getPiece().getPlayer().equals(tempDestination.getPiece().getPlayer())) {
                            throw new InvalidStepException("Another Piece blocks the path");
                        }
                        col--;
                        break;
                    } else {
                        throw new InvalidStepException("Invalid step in range");
                    }
                default:
                    throw new InvalidStepException("No correct direction somehow");
            }
        }
    }

    public boolean checkValidMove(int row, int col, int dRow, int dCol)
            throws InvalidMoveException {
        Piece piece = board.getPlayFields()[row][col].getPiece();
        Field destination = board.getPlayFields()[dRow][dCol];
        //Check if the destination Field is not water
        if (destination.isPlayable()) {
            //Check if the destination Field is within range of the Piece
            if (piece == null || piece.isValidRange(row, col, dRow, dCol)) {
                //Check if the destination Field is empty, move there if it is
                if (!destination.isOccupied()) {
                    return true;
                } else {
                    //Check if the Piece on the destination Field belongs to this Player
                    if (destination.getPiece().getPlayer() != this) {
                        return true;
                    } else {
                        throw new OccupiedFieldException(this);
                    }
                }
            } else {
                throw new InvalidRangeException(this, piece.getRank());
            }
        } else {
            throw new UnplayableFieldException(this);
        }
    }

    /**
     * Occurs when trying to move a Piece this Player owns to a Field containing a Piece belonging to the other Player.
     * Compares the Ranks of the two Pieces. The lower ranking Piece is removed from its Field and the Board. If the
     * attacking Piece has a higher Rank, it also moves into the Field it is attacking to. If both Pieces have the same
     * Rank, they are both removed from their Field and the Board.
     *
     * @param ownField    the Field this Player attacks from (cannot be null)
     * @param destination the Field this Player attacks to (cannot be null)
     */
    public void attack(Field ownField, Field destination) {
        Piece piece = ownField.getPiece();
        int result = piece.compare(destination.getPiece());
        if (result > 0) {
            destination.getPiece().getPlayer().getPieces().remove(destination.getPiece());
            destination.getPiece().getPlayer().sendToGraveyard(destination.getPiece());
            destination.setPiece(piece);
            ownField.setPiece(null);
        } else if (result < 0) {
            pieces.remove(piece);
            sendToGraveyard(piece);
            ownField.setPiece(null);
        } else if (result == 0) {
            destination.getPiece().getPlayer().getPieces().remove(destination.getPiece());
            destination.getPiece().getPlayer().sendToGraveyard(destination.getPiece());
            destination.setPiece(null);
            pieces.remove(piece);
            sendToGraveyard(piece);
            ownField.setPiece(null);
        }
    }

    public void sendToGraveyard(Piece piece){
        int rank = piece.getRank().toInt();
        Piece[] graveRank = graveyard[rank];
        for(int i = 0; i < graveRank.length; i++){
            if(graveRank[i] == null){
                graveRank[i] = piece;
                break;
            }
        }
    }

    /**
     * Accepts user input to select a valid row or column, defined as an integer ranging from 0-9 (including 9)
     *
     * @return integer ranging from 0 to 9 (including 9)
     * @throws InvalidCoordinateException when the user input is not an integer ranging from 0-9 (including 9)
     */
    public int chooseCoordinate() throws InvalidCoordinateException {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int input = in.nextInt();
            if (0 <= input && input <= 9) {
                return input;
            }
        }
        throw new InvalidCoordinateException(this);
    }

    public Rank chooseRank() throws InvalidCoordinateException {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int input = in.nextInt();
            if (0 <= input && input <= 11) {
                return Rank.toEnum(input);
            }
        }
        throw new InvalidCoordinateException(getColor() + ", please choose a number between 0 and 11");
    }

    public void printPieces() {
        for (Piece piece : pieces) {
            System.out.println(piece.toString());
        }
    }

}
