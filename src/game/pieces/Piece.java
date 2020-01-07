package game.pieces;

import game.Player;
import game.extra.Rank;

public class Piece {
    private Rank rank;
    private Player player;
    private int range = 1;

    public Piece(){

    }

    public Piece(Player player, Rank rank){
        this.player = player;
        this.rank = rank;
    }

    public Piece(Player player, int rank){
        this.player = player;
        this.rank = Rank.toEnum(rank);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Compares this Piece to another Piece.
     * @param piece to compare this Piece against
     * @return Positive int when this Piece has a higher rank.
     * 0 as int when ranks are equal
     * Negative int when this Piece has a lower rank
     */
    public int attack(Piece piece){
        return rank.toInt() - piece.getRank().toInt();
    }

    public String toString(){
        return player.getColor().toString() + " " + getRank().toString();
    }

    public String toCode(){
        String rank = getRank().toInt() + "";
        if (rank.length() < 2){
            rank = rank + " ";
        }
        return player.getColor().toChar() + rank;
    }

    /*
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
     */
}
