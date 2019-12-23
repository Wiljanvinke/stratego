package game;

import game.extra.Color;
import game.pieces.*;

import java.util.ArrayList;

public class Player {
    private String name;
    private Color color;
    private ArrayList<Piece> pieces;


    public Player(Color color) {
        this.color = color;
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

    public void printPieces(){
        for (Piece piece : pieces) {
            System.out.println(piece.toString());
        }
    }
}
