package model;


//Состояние игры
public class Game {

    public enum State {
        X_MOVE("Ход крестиков"),
        O_MOVE("Ход ноликов"),
        X_WINS("Крестики выиграли"),
        O_WINS("Нолики выиграли"),
        DRAW("Ничья");

        public final String name;
        State(String name) {
            this.name = name;
        }
    }

    //Размер поля
    final int size;
    public int getSize()    { return size;  }
    //Поле игры (координаты отсчитываем от верхнего левого угла)
    final Cell[][] field;
    public Cell[][] getField() { return field; }
    //Состояние игры
    State state = State.X_MOVE;
    public State getState() {
        return  state;
    }
    //Счетчик ходов для определения ничьей
    private int turnCounter = 0;


    public Game(int size)
    {
        this.size = size;
        field = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = Cell.EMPTY;
            }
        }
    }

    public Game()
    {
        this(3);
    }

    /*Ход
    * @param x координата по горизонтали (столбец)
    * @param y координата по вертикали (строка)
    */
    public void move( int x, int y ) throws UserException
    {
        if( x < 0 || x >= size )
            throw new UserException( "x = " + x + "за пределами поля. х должен быть от 0 до " + ( size - 1 ) );
        if( y < 0 || y >= size )
            throw new UserException( "y = " + y + "за пределами поля. y должен быть от 0 до " + ( size - 1 ) );
        if( field[x][y] != Cell.EMPTY )
            throw new UserException( "Ячейка занята x = " + x + " y = " + y );

        switch( state ) {
        case X_MOVE:
            field[x][y] = Cell.X;
            break;
        case O_MOVE:
            field[x][y] = Cell.O;
            break;
        default:
            throw new UserException( "Ход невозможен!" );
    }

        turnCounter++;

        //проверки на победу или ничью
        boolean victory = checkIfVictory();
        boolean draw = ( turnCounter >= Math.pow( size, 2 ) );

        //переключение состояния
        if( victory )
            state = ( state == State.X_MOVE ) ? State.X_WINS : State.O_WINS;
        else if( draw )
            state = State.DRAW;
        else
            state = ( state == State.X_MOVE ) ? State.O_MOVE : State.X_MOVE;
    }

    private boolean checkIfVictory()
    {
        Cell cell;
        boolean same;

        //проверим столбцы
        for (int x = 0; x < size; x++) {
           if( field[x][0] == Cell.EMPTY )
               continue;     //столбец x не состоит из всех крестиков/ноликов
            same = true;
            for (int y = 1; y < size; y++) {
                if( field[x][y] != field[x][0] ) {
                    same = false;
                    break;     //столбец x не состоит из всех крестиков/ноликов
                }
            }
            //в столбце x все крестики или нолики
            if( same )
                return  true;
        }

        //проверим строки
       for (int y = 0; y < size; y++) {
            if( field[0][y] == Cell.EMPTY )
                continue;
            same = true;
            for (int x = 1; x < size; x++) {
                if (field[x][y] != field[0][y]) {
                    same = false;
                    break;
                }
            }
            if( same )
                return  true;
        }

        //проверим диагонали
        if( field[0][0] != Cell.EMPTY ) {
            same = true;
            for( int x = 1, y = 1; x < size; x++, y++ ) {
                if (field[x][y] != field[0][0]) {
                    same = false;
                    break;
                }
            }
            if( same )
                return  true;
        }

        if( field[size - 1][0] != Cell.EMPTY ) {
            same = true;
            for( int x = size - 2, y = 1; x >= 0; x--, y++ ) {
               if( field[x][y] != field[size - 1][0] ) {
                   same = false;
                   break;
               }
            }
            if( same )
                return  true;
        }

        return false;
    }

    public boolean IsOver()
    {
        return state == State.X_WINS || state == State.O_WINS || state == State.DRAW;
    }
}
