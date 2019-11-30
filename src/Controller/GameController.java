package Controller;

import Model.GameModel;
import View.MainView;

import java.awt.*;
import java.awt.event.*;


public class GameController implements ActionListener, MouseMotionListener, MouseListener, ComponentListener {
    private GameModel gameModel;
    private MainView mainView;
    private Thread game;
    private boolean gameIsBeingPlayed;

    public GameController(GameModel model, MainView view) {
        gameIsBeingPlayed = false;
        gameModel = model;
        mainView = view;
        mainView.getButtonPanel().addActionListenerForAdvance(this);
        mainView.getButtonPanel().addActionListenerForAutoGenerate(this);
        mainView.getButtonPanel().addActionListenerForSimulate(this);
        mainView.getButtonPanel().addActionListenerForStopSimulate(this);
        mainView.getButtonPanel().addActionListenerForClear(this);
        mainView.getBoardView().addMouseMotionListenerToController(this);
        mainView.getBoardView().addMouseListenerToController(this);
        mainView.getBoardView().addComponentListenerToController(this);
    }

    public void setGameBeingPlayed(boolean isBeingPlayed) {
        if (isBeingPlayed) {
            game = new Thread(gameModel);
            game.start();
            gameIsBeingPlayed = true;
        } else {
            game.interrupt();
            gameIsBeingPlayed = false;
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainView.getButtonPanel().getAutoGenerate()) {
            gameModel.randomlyFillBoard();
        }
        if (e.getSource() == mainView.getButtonPanel().getClear()) {
            gameModel.resetBoard();
            if (gameIsBeingPlayed) {
                setGameBeingPlayed(false);
            }
        }
        if (e.getSource() == mainView.getButtonPanel().getSimulate()) {
            if (gameIsBeingPlayed) {
                return;
            } else {
                setGameBeingPlayed(true);
            }
        }
        if (e.getSource() == mainView.getButtonPanel().getStopSimulate()) {
            if (gameIsBeingPlayed) {
                setGameBeingPlayed(false);
            }
        }
        if (e.getSource() == mainView.getButtonPanel().getAdvanceOneStep()) {
            gameModel.advanceOneStep();
        }
    }

    public void componentResized(ComponentEvent e) {
        int southPanelHeight = mainView.getButtonPanel().getHeight();
        Dimension dimension = mainView.getBoardView().getGameBoardDimensions();
        dimension = new Dimension(mainView.getWidth()/mainView.getBoardView().getCellSize()-2,
                ((mainView.getHeight()/mainView.getBoardView().getCellSize()-2)) - (southPanelHeight/5));
        mainView.getBoardView().setDimensions(dimension);
        mainView.getBoardView().updateBoardSize();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        gameModel.pointNeedsToBeAddedOrRemoved(e);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        gameModel.addPoint(e);
    }

    public void mouseMoved(MouseEvent e) {
    }
}
