package Controller;

import Model.GameModel;
import View.MainView;
import View.ResizePanel;
import View.ThreadPanel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


public class GameController implements ActionListener, MouseMotionListener, MouseListener, ComponentListener {
    private GameModel gameModel;
    private MainView mainView;
    private Thread game;
    private boolean gameIsBeingPlayed;
    private ResizePanel resizePanel;
    private ThreadPanel threadPanel;


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

                                        // Creating extra windows for later calls;
        // custom view for resizing panel.... you can also resize the panel by expanding the window
        resizePanel = new ResizePanel();
        resizePanel.addActionListenerForEnterButton(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String stringWidth = resizePanel.getWidthTextField().getText();
                String stringHeight = resizePanel.getHeightTextField().getText();
                if (stringHeight.equals("") || stringWidth.equals("")) {
                    throw new IllegalArgumentException();
                }
                int width = Integer.parseInt(stringWidth);
                int height = Integer.parseInt(stringHeight);
                System.out.println(width + height);
                if (width > 500 || width <10 || height > 500 || height < 10) {
                    throw new IllegalArgumentException();
                   } else {
                    mainView.getBoardView().setDimensions(new Dimension(width, height));
                    mainView.getBoardView().updateBoardSize();
                    mainView.updateFameSize(width+2,height+2);
                    resizePanel.dispose();
                }
            }
        });

        // Custom view for updating threads
        threadPanel = new ThreadPanel();
        threadPanel.addActionListenerForEnterButton(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String typedNumber = threadPanel.getNumberOfMilliseconds().getText();
                Integer convertText = Integer.parseInt(typedNumber);
                gameModel.setDelayBetweenUpdates(convertText);
                threadPanel.dispose();
            }
        });

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
            resizePanel.setVisible(true);
            resizePanel.setTitle("Resize the Panel");
            centerNewPane(resizePanel);
        }

        if (e.getSource() == mainView.getMenuBarImpl().getThreadUpdateTime()) {
            threadPanel.setVisible(true);
            threadPanel.setTitle("Update Delay For Thread");
            centerNewPane(threadPanel);
        }

        if (e.getSource() == mainView.getMenuBarImpl().getCellConditions()) {
            JFrame cellConditionsFrame = new JFrame();
            JPanel cellConditionsPanel = new JPanel();

            cellConditionsPanel.setLayout(new FlowLayout());
            JLabel setSurviveConditions = new JLabel("Enter the conditions for the Cell to survive");
            JLabel setBirthConditions = new JLabel("Enter the conditions for a new cell to be born");
            Integer[] ints = {1,2,3,4,5,6,7,8};
            ListModel options = new DefaultListModel();
            ((DefaultListModel) options).setSize(8);
            ((DefaultListModel) options).add(0,1);
            ((DefaultListModel) options).add(1,2);
            ((DefaultListModel) options).add(2,3);
            ((DefaultListModel) options).add(3,4);
            ((DefaultListModel) options).add(4,5);
            ((DefaultListModel) options).add(5,6);
            ((DefaultListModel) options).add(6,7);
            ((DefaultListModel) options).add(7,8);
            JList surviveList = new JList(options);
            JList birthList = new JList(options);
            JButton done = new JButton("Done");
            surviveList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                }
            });
            birthList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                }
            });
            done.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
            surviveList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            birthList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            cellConditionsPanel.add(setSurviveConditions);
            cellConditionsPanel.add(surviveList);
            cellConditionsPanel.add(setBirthConditions);
            cellConditionsPanel.add(birthList);
            cellConditionsPanel.add(done);
            cellConditionsFrame.add(cellConditionsPanel);
            cellConditionsFrame.setSize(400,200);
            cellConditionsFrame.setVisible(true);


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
        int northPanel = mainView.getMenuBarImpl().getHeight();
        int southPanel = mainView.getButtonPanel().getHeight();
        int frameView = mainView.getHeight();
        int width = (mainView.getWidth()) / mainView.getBoardView().getCellSize()-2;
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

    private void centerNewPane(JFrame frame) {
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth())/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight())/2);
    }
}
