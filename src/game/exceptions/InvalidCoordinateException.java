package game.exceptions;

import game.Player;

public class InvalidCoordinateException extends InvalidMoveException {
    public InvalidCoordinateException(String errorMessage){
        super(errorMessage);
    }

    public InvalidCoordinateException(Player player){
        super(player.getColor() + ", please choose a number between 0 and 9");
    }
}
