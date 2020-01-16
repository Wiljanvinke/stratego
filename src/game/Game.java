package game;

import game.exceptions.InvalidMoveException;
import game.extra.Color;
import game.extra.Rank;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private boolean turn;
    private String winner;
    private Board board;
    private Player player1;
    private Player player2;

    public Game(){
        board = new Board();
        //board.printBoardPlayable();
        player1 = new Player(Color.RED, board);
        player2 = new Player(Color.BLUE, board);
        setupRandomBoard();
        //setupTestBoard();
        board.printBoardPieces();
        while(winner == null) {
            play();
            checkWinner();
        }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board setupRandomBoard(){
        Collections.shuffle(player1.getPieces());
        Collections.shuffle(player2.getPieces());
        //setup player1
        int index = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 10; j++){
                board.getPlayFields()[i][j].setPiece(player1.getPieces().get(index));
                index++;
            }
        }
        //setup player2
        index = 0;
        for(int i = 6; i < 10; i++){
            for(int j = 0; j < 10; j++){
                board.getPlayFields()[i][j].setPiece(player2.getPieces().get(index));
                index++;
            }
        }
        return board;
    }

    public Board setupTestBoard(){
        ArrayList<Piece> pieces1 = player1.getPieces();
        for(Piece piece: pieces1) {
            if (piece.getRank() == Rank.BOMB){
                board.getPlayFields()[3][0].setPiece(piece);
            }
        }
        ArrayList<Piece> pieces2 = player2.getPieces();
        for(Piece piece: pieces2) {
            if (piece.getRank() == Rank.SPY){
                board.getPlayFields()[4][0].setPiece(piece);
            }
        }

        return board;
    }

    public void play(){
        boolean valid = false;
        while(!valid) {
            try {
                if (turn == false) {
                    player1.makeMove();
                    turn = true;
                    valid = true;
                } else if (turn == true) {
                    player2.makeMove();
                    turn = false;
                    valid = true;
                }
                board.printBoardPieces();
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkWinner(){
        if(turn == false && !player1.canMakeMove()){
            winner = player2.getName();
        }
        if(turn == true && !player2.canMakeMove()){
            winner = player1.getName();
        }

    }

}
