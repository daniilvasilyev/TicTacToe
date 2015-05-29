package controller;

import model.Game;
import model.UserException;
import view.Coord;
import view.GameView;
import view.console.ConsoleView;
import view.swing.SwingView;


//Контроллер
public class Main {
      public static void main(String[] args) {
          //todo  - выбор view
          GameView gameview = new ConsoleView();

          //todo - выбор размера игры
          Game game = new Game();

          Coord nextTurn;
          while( !game.IsOver() ) {
              nextTurn = gameview.getTurn();
              try {
                  game.move(nextTurn.x, nextTurn.y);
              }
              catch (UserException exc) {
                  System.out.println( exc.toString() );
              }
              gameview.render(game);
          }
          System.out.println( "Игра окончена" );
    }
}
