package game.exceptions;

public class InvalidFieldException extends InvalidMoveException {
    public InvalidFieldException(String errorMessage){
        super(errorMessage);
    }

    public InvalidFieldException(int row, int col){
        super(row + " " + col + " is not a valid field");
    }
}
