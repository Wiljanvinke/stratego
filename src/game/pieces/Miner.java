package game.pieces;

import game.Player;
import game.extra.Rank;

public class Miner extends Piece {
    public Miner(Player player){
        super(player, 3);
    }

    @Override
    public int compare(Piece piece){
        if (piece.getRank() == Rank.BOMB){
            return 11;
        }
        return rank.toInt() - piece.getRank().toInt();
    }

}
