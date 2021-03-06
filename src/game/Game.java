package game;

import game.exceptions.InvalidCoordinateException;
import game.exceptions.InvalidFieldException;
import game.exceptions.InvalidMoveException;
import game.exceptions.OccupiedFieldException;
import game.extra.ANSI;
import game.extra.Color;
import game.extra.Rank;
import game.pieces.Piece;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private boolean turn;
    private String winner;
    private Board board;
    private Player player1;
    private Player player2;

    public Game(){
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

    public Board setupPlayerBoard(Player player){
        ArrayList<Piece> setupPieces = new ArrayList<>(player.getPieces());
        Collections.sort(setupPieces);
        int[] ranks = new int[12];
        for(Piece piece: setupPieces){
           ranks[piece.getRank().toInt()]++;
        }
        while (!setupPieces.isEmpty()){
            for(int i = 0; i < ranks.length; i++) {
                if(ranks[i] > 0){
                    System.out.println(player.getColor() + " "  +
                            Rank.toEnum(i).toString() + "[" + i + "]" + ": " + ranks[i]);
                }
            }
            board.printBoardPieces();
            System.out.println("> "
                    + player.getColor() + ", please select the rank of the piece you want to place (0-11)");
            try{
                Rank rank = player.chooseRank();
                Piece piece = null;
                for (Piece setupPiece : setupPieces) {
                    if (setupPiece.getRank().equals(rank)) {
                        piece = setupPiece;
                    }
                }
                if (piece == null){
                    throw new InvalidMoveException(player.getColor() +
                            ", you do not have a " + rank.toString().toUpperCase() + " anymore");
                }
                System.out.println("> " + player.getColor() + ", what row do you want to place the " +
                        piece.getRank().toString().toUpperCase() +  " in (0-9)?");
                int dRow = player.chooseCoordinate();
                if((player.getColor().equals(Color.RED) && (0 <= dRow && dRow < 4)) ||
                        (player.getColor().equals(Color.BLUE) && (6 <= dRow && dRow < 10))){
                    System.out.println("> " + player.getColor() +
                            ", what column do you want to place the " +
                            piece.getRank().toString().toUpperCase() +  " in (0-9)?");
                    int dCol = player.chooseCoordinate();
                    Field destination = board.getPlayFields()[dRow][dCol];
                    if(!destination.isOccupied()){
                        destination.setPiece(piece);
                        setupPieces.remove(piece);
                        ranks[piece.getRank().toInt()]--;
                    } else {
                        throw new OccupiedFieldException(player);
                    }
                } else {
                    throw new InvalidMoveException(player.getColor() + ", please select a valid row for your color");
                }
            } catch (InvalidMoveException e){
                System.out.println(e.getMessage());
            }
        }
        return board;
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
            if (piece.getRank() == Rank.SCOUT){
                board.getPlayFields()[3][0].setPiece(piece);
            }
        }
        ArrayList<Piece> pieces2 = player2.getPieces();
        for(Piece piece: pieces2) {
            if (piece.getRank() == Rank.SCOUT){
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
                printGraveYard();
                board.printBoardPieces();
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkWinner(){
        if((turn == false && !player1.canMakeMove()) || !player1.hasFlag()){
            winner = ANSI.ANSI_BLUE + player2.getColor().toString();
        }
        if((turn == true && !player2.canMakeMove()) || !player2.hasFlag()){
            winner = ANSI.ANSI_RED + player1.getColor().toString();
        }
    }

    public void printWinner(){
        System.out.println(winner + " is the winner!");
    }

    public void printGraveYard(){
        StringBuilder s = new StringBuilder(ANSI.ANSI_DAGGER + ANSI.ANSI_RED);
        Piece[][] graveyard1 = player1.getGraveyard();
        Piece[][] graveyard2 = player2.getGraveyard();
        for(Piece[] graveRank: graveyard1){
            for(Piece piece: graveRank){
                if(piece != null){
                    s.append(piece.toCode().trim() + ", ");
                }
            }
        }
        System.out.println(s.append(ANSI.ANSI_RESET));
        s = new StringBuilder(ANSI.ANSI_DAGGER + ANSI.ANSI_BLUE);
        for(Piece[] graveRank: graveyard2){
            for(Piece piece: graveRank){
                if(piece != null){
                    s.append(piece.toCode().trim() + ", ");
                }
            }
        }
        System.out.println(s.append(ANSI.ANSI_RESET));
    }

    public void start(){
        board = new Board();
        //board.printBoardPlayable();
        player1 = new Player(Color.RED, board);
        player2 = new Player(Color.BLUE, board);
        setupPlayerBoard(player1);
        setupPlayerBoard(player2);
        //setupRandomBoard();
        //setupTestBoard();
        printGraveYard();
        board.printBoardPieces();
        while(winner == null) {
            play();
            checkWinner();
        }
        printWinner();
    }
}
