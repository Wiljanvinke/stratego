package game.pieces;

import game.Player;

public class Bomb extends Piece {
    private int range = 0;

    public Bomb(Player player){
        super(player, 11);
        setRange(range);
    }
}
