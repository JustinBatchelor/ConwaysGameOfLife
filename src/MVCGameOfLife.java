import Controller.GameController;
import Model.GameModel;
import View.BoardView;
import View.MainView;

import javax.swing.*;
import java.awt.*;

public class MVCGameOfLife {

    public static void main(String[] args) {
        BoardView gameBoard = new BoardView();
        MainView mainView = new MainView(gameBoard);
        GameModel mainModel = new GameModel(gameBoard);
        GameController gameController = new GameController(mainModel, mainView);

        mainView.setTitle("Conway's Game Of Life");
        mainView.setMinimumSize(new Dimension(300,300));
        mainView.setMaximumSize(new Dimension(5000,5000));
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setVisible(true);
        mainView.pack();
    }
}