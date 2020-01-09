package game.exceptions;

import game.Player;

public class OccupiedFieldException extends InvalidMoveException {
    public OccupiedFieldException(String errorMessage){
        super(errorMessage);
    }

    public OccupiedFieldException(Player player){
        super(player.getColor() + ", this field is already occupied by your own piece");
    }
}
