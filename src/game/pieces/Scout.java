package game.pieces;

import game.Player;

public class Scout extends Piece {
    private int range = 9;

    public Scout(Player player){
        super(player, 2);
        setRange(range);
    }
}
