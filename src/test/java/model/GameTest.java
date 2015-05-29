package model;

import org.junit.Test;
import org.omg.CORBA.ULongLongSeqHolder;

import static org.junit.Assert.assertEquals;


//Тест процесса игры
public class GameTest {

    @Test
    public void testSimple() throws UserException {
        Game game = new Game();
        assertEquals("По-умолчанию игра 3x3", 3, game.size);
        assertEquals(Game.State.X_MOVE, game.state);

        // крестики - Ходим в верхний левый угол
        game.move(0, 0);
        assertEquals(Cell.X, game.field[0][0]);

    }

    @Test
    public void testGame1() throws UserException
    {
        Game game = new Game();

        //ход крестиков - Ходим в верхний левый угол
        game.move(0, 0);
        assertEquals(Cell.X, game.field[0][0]);
        assertEquals(Game.State.O_MOVE, game.getState() );
        //нолики
        game.move(1, 0);
        assertEquals(Cell.O, game.field[1][0]);
        assertEquals(Game.State.X_MOVE, game.getState() );
        //крестики
        game.move(0, 1);
        assertEquals(Cell.X, game.field[0][1]);
        assertEquals(Game.State.O_MOVE, game.getState() );
        //нолики
        game.move(1, 1);
        assertEquals(Cell.O, game.field[1][1]);
        assertEquals(Game.State.X_MOVE, game.getState() );
        //крестики выиграли, заполнив верхнюю строку
        game.move(0, 2);
        assertEquals(Cell.X, game.field[0][2]);
        assertEquals(Game.State.X_WINS, game.getState() );
    }

    //todo - тесты других игровых ситуаций

    @Test( expected = UserException.class )
    public void testWrongTurn() throws UserException
    {
        Game game = new Game();
        game.move(5, 0);
    }
}
