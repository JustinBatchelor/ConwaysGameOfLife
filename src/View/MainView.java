package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private ButtonPanel buttonPanel;
    private BoardView boardView;

    public MainView(BoardView view) {
        buttonPanel = new ButtonPanel();
        boardView = view;
        add(buttonPanel, BorderLayout.SOUTH);
        add(boardView, BorderLayout.CENTER);

    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

//    public GameBoard getGameBoard() {
//        return gameBoard;
//    }
    public BoardView getBoardView() {
        return boardView;
    }
}
