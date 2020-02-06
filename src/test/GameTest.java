package test;

import game.Board;
import game.Game;
import game.Player;
import game.extra.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class GameTest {

    Game game;
    Board board;
    Player player1;
    Player player2;

    @BeforeEach
    public void setUp() {
        game = new Game();
        board = new Board();
        player1 = new Player(Color.RED, board);
        player2 = new Player(Color.BLUE, board);
    }


    @Test
    void checkWinner() {
        assertEquals(game.getWinner() == player2.getName(), !player1.hasFlag());
    }
}
