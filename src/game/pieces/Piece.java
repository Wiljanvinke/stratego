package game.pieces;

import game.Player;

public class Piece {
    private int rank;
    private Player player;

    public Piece(){

    }

    public Piece(Player player, int rank){
        this.player = player;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public String toString(){
        switch (rank){
            case 0:
                return player.getColor() + " Flag";
            case 1:
                return player.getColor() + " Spy";
            case 2:
                return player.getColor() + " Scout";
            case 3:
                return player.getColor() + " Miner";
            case 4:
                return player.getColor() + " Sergeant";
            case 5:
                return player.getColor() + " Lieutenant";
            case 6:
                return player.getColor() + " Captain";
            case 7:
                return player.getColor() + " Major";
            case 8:
                return player.getColor() + " Colonel";
            case 9:
                return player.getColor() + " General";
            case 10:
                return player.getColor() + " Marshall";
            case 11:
                return player.getColor() + " Bomb";
            default:
                return "Invalid";
        }
    }
}