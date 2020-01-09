package game.exceptions;

import game.Player;

public class SelectEmptyException extends InvalidMoveException {
    public SelectEmptyException(String errorMessage){
        super(errorMessage);
    }

    public SelectEmptyException(Player player){
        super(player.getColor() + ", this is an empty field");
    }
}
