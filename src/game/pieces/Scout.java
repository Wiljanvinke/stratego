package game.pieces;

import game.Player;

public class Scout extends Piece {
    private int range = 10;

    public Scout(Player player){
        super(player, 2);
    }
}
