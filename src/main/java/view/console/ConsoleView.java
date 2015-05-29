package view.console;

import model.Game;
import view.Coord;
import view.GameView;

import java.util.Scanner;

//Запуск консольного варианта игры
public class ConsoleView implements GameView {
    public ConsoleView()
    {
        System.out.println("Игра Крестики-нолики");
        System.out.println("====================");
    }

    @Override
    public void render( Game game )
    {
        for (int y = 0; y < game.getSize() ; y++) {
            for (int x = 0; x < game.getSize(); x++) {
                System.out.printf( "%s", game.getField()[x][y].presetation );
            }
            System.out.println();
        }

        System.out.println( game.getState().name );
    }

    @Override
    public Coord getTurn()
    {
        System.out.println( "Введите x и y через пробел" );
        Scanner scanner = new Scanner( System.in );
        //todo проверка правильности ввода
        return new Coord( scanner.nextInt(), scanner.nextInt() );
    }
}
