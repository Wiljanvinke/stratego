package game.exceptions;

import game.Player;
import game.extra.Rank;

public class InvalidRangeException extends InvalidMoveException {
    public InvalidRangeException(String errorMessage){
        super(errorMessage);
    }

    public InvalidRangeException(Player player, Rank rank){
        super(player.getColor() + ", this is not a valid range for a " + rank);
    }
}
