package game.exceptions;

import game.Player;

public class InvalidPieceException extends InvalidMoveException {
    public InvalidPieceException(String errorMessage){
        super(errorMessage);
    }

    public InvalidPieceException(Player player){
        super(player.getColor() + ", this is not one of your pieces");
    }
}
