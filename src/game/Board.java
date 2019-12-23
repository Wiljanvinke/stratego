package game;

import game.extra.ANSI;
import game.extra.Color;
import game.pieces.Piece;

public class Board {
    private Field[][] playFields;

    public Board(){
        playFields = new Field[10][10];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                playFields[i][j] = new Field();
            }
        }
        playFields[4][2].setPlayable(false);
        playFields[4][3].setPlayable(false);
        playFields[4][6].setPlayable(false);
        playFields[4][7].setPlayable(false);
        playFields[5][2].setPlayable(false);
        playFields[5][3].setPlayable(false);
        playFields[5][6].setPlayable(false);
        playFields[5][7].setPlayable(false);
    }

    public Field[][] getPlayFields() {
        return playFields;
    }

    public void setPlayFields(Field[][] playFields) {
        this.playFields = playFields;
    }

    public void printBoardPlayable(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(playFields[i][j].isPlayable());
            }
            System.out.println();
        }
    }

    public void printBoardPieces(){
        for (int i = 0; i < 10; i++){
            System.out.println("-------------------------------------------------------------");
            for (int j = 0; j < 10; j++){
                System.out.print("|");
                Piece piece = playFields[i][j].getPiece();
                if(piece != null) {
                    if (piece.getPlayer().getColor() == Color.RED){
                        System.out.print(" " + ANSI.ANSI_RED + playFields[i][j].getPiece().toCode() + ANSI.ANSI_RESET + " ");
                    }
                    if (piece.getPlayer().getColor() == Color.BLUE){
                        System.out.print(" " + ANSI.ANSI_BLUE + playFields[i][j].getPiece().toCode() + ANSI.ANSI_RESET + " ");
                    }
                } else {
                    if (playFields[i][j].isPlayable()){
                        System.out.print("     ");
                    } else {
                        System.out.print("  X  ");
                    }
                }
                if (j == 9){
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------");
    }
}
