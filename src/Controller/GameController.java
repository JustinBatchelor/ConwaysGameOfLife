package Controller;

import Model.GameModel;
import View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameController implements ActionListener, MouseMotionListener, MouseListener, ComponentListener {
    private GameModel gameModel;
    private MainView mainView;
    private Thread game;
    private boolean gameIsBeingPlayed;
    private CellConditionViewController cellConditionViewController;
    private ThreadViewController threadViewController;
    private ResizeViewController resizeViewController;


    public GameController(GameModel model, MainView view) {
        gameIsBeingPlayed = false;
        gameModel = model;
        mainView = view;
        mainView.getButtonPanel().addActionListenerForAdvance(this);
        mainView.getButtonPanel().addActionListenerForSimulate(this);
        mainView.getButtonPanel().addActionListenerForStopSimulate(this);
        mainView.getBoardView().addMouseMotionListenerToController(this);
        mainView.getBoardView().addMouseListenerToController(this);
        mainView.getBoardView().addComponentListenerToController(this);
        mainView.getMenuBarImpl().addActionListenerForAutoGenerate(this);
        mainView.getMenuBarImpl().addActionListenerForClear(this);
        mainView.getMenuBarImpl().addActionListenerForResize(this);
        mainView.getMenuBarImpl().addActionListenerForCellConditions(this);
        mainView.getMenuBarImpl().addActionListenerForTorus(this);
        mainView.getMenuBarImpl().addActionListenerForThread(this);
        threadViewController = new ThreadViewController(gameModel);
        resizeViewController = new ResizeViewController(gameModel);
        cellConditionViewController = new CellConditionViewController(gameModel);

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
        if (e.getSource() == mainView.getMenuBarImpl().getAutoGenerate()) {
            gameModel.randomlyFillBoard();
        }
        if (e.getSource() == mainView.getMenuBarImpl().getClear()) {
            gameModel.resetBoard();
            if (gameIsBeingPlayed) {
                setGameBeingPlayed(false);
            }
        }
        if (e.getSource() == mainView.getMenuBarImpl().getResize()) {
            resizeViewController.getResizeView().setVisible(true);
            resizeViewController.getResizeView().setTitle("Resize The Panel");
            centerNewPane(resizeViewController.getResizeView());
        }
        if (e.getSource() == mainView.getMenuBarImpl().getThreadUpdateTime()) {
            threadViewController.getThreadView().setVisible(true);
            threadViewController.getThreadView().setTitle("Update Delay For Thread");
            centerNewPane(threadViewController.getThreadView());
        }

        if (e.getSource() == mainView.getMenuBarImpl().getCellConditions()) {
            cellConditionViewController.getCellConditionsView().resetLabel();
            cellConditionViewController.getCellConditionsView().setVisible(true);
            cellConditionViewController.getCellConditionsView().setTitle("Cell Conditions");
            centerNewPane(cellConditionViewController.getCellConditionsView());
        }
        if (e.getSource() == mainView.getMenuBarImpl().getTorus()) {
            // Should add some sort of ui for the user to know it is activated
            boolean torusStatus = gameModel.getTorusStatus();
            gameModel.setTorusMode(!torusStatus);
            System.out.println("Torus Mode Activated " + gameModel.getTorusStatus());
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
            if (gameModel.getTorusStatus()) { gameModel.advanceOnStepTorus(); }
            else { gameModel.advanceOneStep(); } }
    }

    public void componentResized(ComponentEvent e) {
        int width = (mainView.getWidth()) / mainView.getBoardView().getCellSize()-4;
        int height = (mainView.getHeight()) / mainView.getBoardView().getCellSize()-12;
        Dimension dimension = new Dimension(width, height);
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


    public void centerNewPane(JFrame frame) {
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth())/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight())/2);
    }
}
