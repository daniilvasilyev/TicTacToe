package model;

//Состояние клетки поля
public enum Cell {
    X( "X" ), O ( "O" ), EMPTY ( "_" );

    public final String presetation;
    Cell(String x)
    {
        presetation = x;
    }
}
