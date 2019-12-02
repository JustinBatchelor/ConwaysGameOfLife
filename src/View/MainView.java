package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private ButtonPanel buttonPanel;
    private BoardView boardView;
    private MenuBarImpl menuBarImpl;

    public MainView(BoardView view) {
        menuBarImpl = new MenuBarImpl();
        buttonPanel = new ButtonPanel();
        boardView = view;
        add(buttonPanel, BorderLayout.SOUTH);
        add(boardView, BorderLayout.CENTER);
        add(menuBarImpl, BorderLayout.NORTH);
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    public BoardView getBoardView() {
        return boardView;
    }
    public MenuBarImpl getMenuBarImpl() {return menuBarImpl;}
}
