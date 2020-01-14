package game.exceptions;

import game.Player;

public class OccupiedFieldException extends InvalidMoveException {
    public OccupiedFieldException(String errorMessage){
        super(errorMessage);
    }

    public OccupiedFieldException(Player player){
        super(player.getColor() + ", you tried to move on or over a Field with your own Piece");
    }
}
