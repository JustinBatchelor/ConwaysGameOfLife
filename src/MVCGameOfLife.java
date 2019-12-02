import Controller.GameController;
import Model.GameModel;
import View.BoardView;
import View.InfoView;
import View.MainView;

import javax.swing.*;
import java.awt.*;

public class MVCGameOfLife {

    public static void main(String[] args) {
        InfoView readME = new InfoView();
        BoardView gameBoard = new BoardView();
        MainView mainView = new MainView(gameBoard);
        GameModel mainModel = new GameModel(gameBoard);
        GameController gameController = new GameController(mainModel, mainView);

        mainView.setTitle("Conway's Game Of Life");
        mainView.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - mainView.getWidth())/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - mainView.getHeight())/2);
        readME.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - readME.getWidth())/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height));
        mainView.setMinimumSize(new Dimension(370,450));
        mainView.setMaximumSize(new Dimension(5000,5000));
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setVisible(true);
        readME.setVisible(true);
        mainView.pack();
    }
}