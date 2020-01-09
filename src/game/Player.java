package game;

import game.exceptions.*;
import game.extra.Color;
import game.pieces.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private Color color;
    private ArrayList<Piece> pieces;
    private Board board;



    public Player(Color color, Board board) {
        this.color = color;
        this.board = board;
        pieces = new ArrayList<Piece>();

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
            newPiece = new Piece(this, 11);
            pieces.add(newPiece);
        }

        printPieces();
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

    public void makeMove() throws InvalidMoveException {
        //Select a field your piece is on
        System.out.println(this.color + ", what row is the piece in (0-9)?");
        int row = chooseCoordinate();

        System.out.println(this.color + ", what column is the piece in (0-9)?");
        int col = chooseCoordinate();
        Field ownField = board.getPlayFields()[row][col];
        if (ownField.isOccupied()) {
            Piece piece = ownField.getPiece();
            System.out.println("Row: " + row + ", Column: " + col + ", Piece: " + piece.toString());

            if (piece.getPlayer() == this) {
                //Select the field you want to move to
                System.out.println(this.color + ", what row do you want to move the piece to (0-9)?");
                int dRow = chooseCoordinate();
                System.out.println(this.color + ", what column do you want to move the piece to (0-9)?");
                int dCol = chooseCoordinate();
                Field destination = board.getPlayFields()[dRow][dCol];

                if (destination.isPlayable()) {
                    if (piece.isValidRange(row, col, dRow, dCol)) {
                        if (!destination.isOccupied()) {
                            destination.setPiece(piece);
                            ownField.setPiece(null);
                        } else {
                            if (destination.getPiece().getPlayer() != this) {
                                attack(piece, ownField, destination);
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
            } else {
                throw new InvalidPieceException(this);
            }
        } else {
            throw new SelectEmptyException(this);
        }

    }

    public void attack(Piece piece, Field ownField, Field destination) {
        int result = piece.compare(destination.getPiece());
        if (result > 0) {
            destination.setPiece(piece);
            ownField.setPiece(null);
        } else if (result < 0) {
            ownField.setPiece(null);
        } else if (result == 0) {
            destination.setPiece(null);
            ownField.setPiece(null);
        }
    }

    public int chooseCoordinate() throws InvalidCoordinateException {
        Scanner in = new Scanner(System.in);
        if(in.hasNextInt()) {
            int input = in.nextInt();
            if (0 <= input && input <= 9) {
                return input;
            }
        }
        throw new InvalidCoordinateException(this);
    }

    public void printPieces() {
        for (Piece piece : pieces) {
            System.out.println(piece.toString());
        }
    }

}
