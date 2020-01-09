package game.exceptions;

import game.Player;

public class UnplayableFieldException extends InvalidMoveException {
    public UnplayableFieldException(String errorMessage){
        super(errorMessage);
    }

    public UnplayableFieldException(Player player){
        super(player.getColor() + ", you cannot move into water");
    }
}
