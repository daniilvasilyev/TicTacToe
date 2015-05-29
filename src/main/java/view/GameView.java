package view;

import model.Game;
import view.Coord;


//Интерфейс игры
public interface GameView {

    void render(Game game);
    Coord getTurn();
}
