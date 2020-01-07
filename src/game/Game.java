package game;

import game.extra.Color;

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
        board.printBoardPieces();
        //while(winner == null) {
            play();
        //}
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

    public void play(){
        if(turn == false) {
            player1.makeMove();
            turn = true;
        } else if (turn == true) {
            player2.makeMove();
            turn = false;
        }
        board.printBoardPieces();
    }

}
