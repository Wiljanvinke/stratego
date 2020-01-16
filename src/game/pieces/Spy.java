package game.pieces;

import game.Player;
import game.extra.Rank;

public class Spy extends Piece {
    public Spy(Player player){
        super(player, 1);
    }

    @Override
    public int compare(Piece piece){
        if (piece.getRank() == Rank.MARSHALL){
            return 10;
        }
        return rank.toInt() - piece.getRank().toInt();
    }

}
