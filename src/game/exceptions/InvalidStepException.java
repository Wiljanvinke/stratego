package game.exceptions;

import game.Player;

public class InvalidStepException extends InvalidMoveException {
    public InvalidStepException(String errorMessage){
        super(errorMessage);
    }

}
