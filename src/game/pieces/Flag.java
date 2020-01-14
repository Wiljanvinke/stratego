package game.pieces;

import game.Player;

public class Flag extends Piece {
    private int range = 0;

    public Flag(Player player){
        super(player, 0);
        setRange(range);
    }
}
