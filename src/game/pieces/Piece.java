package game.pieces;

import game.Player;
import game.extra.Rank;

public class Piece implements Comparable<Piece> {
    protected Rank rank;
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
    public int compare(Piece piece){
        return rank.toInt() - piece.getRank().toInt();
    }

    /**
     * Checks whether the move entered is within the Piece's range
     * @param row the row this piece is moving from
     * @param col the column this piece is moving from
     * @param dRow the row this piece is moving to
     * @param dCol the column this piece is moving to
     * @return true if the destination field is within the Piece's range
     */
    public boolean isValidRange(int row, int col, int dRow, int dCol){
        boolean validVert = (0 < Math.abs(row - dRow) && Math.abs(row - dRow) <= getRange() && col - dCol == 0);
        boolean validHor = (0 < Math.abs(col - dCol) && Math.abs(col - dCol) <= getRange() && row - dRow == 0);
        return validVert || validHor;
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

    @Override
    public int compareTo(Piece piece) {
        return rank.toInt() - piece.getRank().toInt();
    }
}
