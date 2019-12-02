package Model;


import View.BoardView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/*
 *                                          We are going to need
 *
 * A list of points that are survivor cells to pass to the view
 * The current window size (Dimensions of the board) to make sure the model and view inline (Remember the board is resizable)
 * The initial conditions set by the user
 *
 */
public class GameModel implements Runnable{

    private int x;
    private int y;

    private BoardView boardView;
    private List<Integer> cellLivesConditions;
    private List<Integer> cellIsBornConditions;
    private Integer delayBetweenUpdates;
    private boolean isTorusModeOn;


    public GameModel(BoardView view) {
        boardView = view;
        delayBetweenUpdates = 300;
        cellIsBornConditions = new ArrayList<>();
        cellLivesConditions = new ArrayList<>();
        setInitialBornConditions();
        setInitialSurviveConditions();
        isTorusModeOn = false;
    }

    public void setCellLivesConditions(List<Integer> liveConditions) {
        cellLivesConditions.addAll(liveConditions);
    }
    public void setCellIsBornConditions(List<Integer> bornConditions) {
        cellIsBornConditions.addAll(bornConditions);
    }

    public void setInitialBornConditions() {
        clearBirthList();
        cellIsBornConditions.add(3);
    }
    public void setInitialSurviveConditions() {
        clearSurviveList();
        cellLivesConditions.add(2);
        cellLivesConditions.add(3);
    }
    public void clearSurviveList() {
        if (!cellLivesConditions.isEmpty()) {
            cellLivesConditions.clear();
        }
    }
    public void clearBirthList(){
        if (!cellIsBornConditions.isEmpty()) {
            cellIsBornConditions.clear();
        }
    }

    public void setTorusMode(boolean bool) {
        isTorusModeOn = bool;
        boardView.setTorusMode(bool);
        boardView.repaint();
    }
    public boolean getTorusStatus() {
        return isTorusModeOn;
    }

    public void randomlyFillBoard() {
        for (x=0; x< boardView.getGameBoardDimensions().width; x++) {
            for (y=0; y< boardView.getGameBoardDimensions().height; y++) {
                if (Math.random() < .2) {
                    addPoint(x,y);
                }
            }
        }
    }
    public void pointNeedsToBeAddedOrRemoved(MouseEvent mouseEvent) {
        int x = (mouseEvent.getPoint().x-11)/((boardView.getCellSize()));
        int y = (mouseEvent.getPoint().y-11)/((boardView.getCellSize()));
        if ((x >= 0) && (x < boardView.getGameBoardDimensions().width) && (y >= 0) && (y < boardView.getGameBoardDimensions().height)) {
            if (boardView.getCells().contains(new Point(x,y))) {
                boardView.removePoint(x,y);
                boardView.repaint();
            } else {
                addPoint(x, y);
            }
        }
    }

    public void addPoint(int x, int y) {
        if (!boardView.getCells().contains(new Point(x,y))) {
            boardView.getCells().add(new Point(x,y));
        }
        boardView.repaint();
    }

    public void addPoint(MouseEvent mouseEvent) {
        int x = (mouseEvent.getPoint().x-11)/((boardView.getCellSize()));
        int y = (mouseEvent.getPoint().y-11)/((boardView.getCellSize()));
        if ((x >= 0) && (x < boardView.getGameBoardDimensions().width) && (y >= 0) && (y < boardView.getGameBoardDimensions().height)) {
            addPoint(x,y);
        }
    }

    public void resetBoard() {
        boardView.resetBoard();
    }

    public void setDelayBetweenUpdates(Integer passedSecondsFromController) {
        delayBetweenUpdates = passedSecondsFromController;
    }

    public void advanceOneStep() {
        boolean[][] gameBoard = new boolean[boardView.getGameBoardDimensions().width + 2][boardView.getGameBoardDimensions().height + 2];
        for (Point p : boardView.getCells()) {
            gameBoard[p.x + 1][p.y + 1] = true;
        }
        ArrayList<Point> survivingCells = new ArrayList<>();
        for (int x = 1; x < gameBoard.length - 1; x++) {
            for (int y = 1; y < gameBoard[0].length - 1; y++) {
                int neighbor = 0;
                if (gameBoard[x-1][y-1]) { neighbor++; }
                if (gameBoard[x][y-1]) { neighbor++; }
                if (gameBoard[x+1][y-1]) { neighbor++; }
                if (gameBoard[x+1][y]) { neighbor++; }
                if (gameBoard[x+1][y+1]) { neighbor++; }
                if (gameBoard[x][y+1]) { neighbor++; }
                if (gameBoard[x-1][y+1]) { neighbor++; }
                if (gameBoard[x-1][y]) { neighbor++; }
                if (gameBoard[x][y]) {
                    for (Integer i : cellLivesConditions) {
                        if (neighbor == i) {
                            survivingCells.add(new Point(x-1,y-1));
                        }
                    }
                } else {
                    for (Integer i : cellIsBornConditions) {
                        if (neighbor == i) {
                            survivingCells.add(new Point(x-1, y-1));
                        }
                    }
                }
            }
        }
        boardView.updateBoard(survivingCells);
    }

    public void advanceOnStepTorus() {
        boolean[][] gameBoard = new boolean[boardView.getGameBoardDimensions().width + 2][boardView.getGameBoardDimensions().height + 2];
        for (Point p : boardView.getCells()) {
            gameBoard[p.x + 1][p.y + 1] = true;
        }
        ArrayList<Point> survivingCells = new ArrayList<>();
        for (int x = 1; x < gameBoard.length - 1; x++) {
            for (int y = 1; y < gameBoard[0].length - 1; y++) {
                int neighbor = 0;
                // Top Left Corner
                if (x == 1 && y == 1) {
                    // need to check diagonal corner
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[x+1][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][y]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][y+1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                }
                // BottomLeft corner
                if (x == 1 && y == gameBoard[0].length - 2) {
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][1]) {neighbor++;}
                    if (gameBoard[1][1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][y-1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                }
                // Top Right corner
                if (x == gameBoard.length - 2 && y == 1) {
                    if (gameBoard[1][1]) { neighbor++; }
                    if (gameBoard[1][2]) { neighbor++; }
                    if (gameBoard[1][gameBoard[0].length-2]) { neighbor++; }
                    if (gameBoard[gameBoard.length-2][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[x-1][1]) {neighbor++;}
                    if (gameBoard[x-1][y+1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][2]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                }
                // Bot Right corner
                if (x==gameBoard.length - 2 && y == gameBoard[0].length - 2) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[1][1]) {neighbor++;}
                    if (gameBoard[1][gameBoard[0].length-2]) {neighbor++;}
                    if (gameBoard[1][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[gameBoard.length-2][1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][1]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                }
                // Left Column
                if (x==1) {
                    if (gameBoard[gameBoard.length-2][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[gameBoard.length-2][y+1]) { neighbor++; }
                    if (gameBoard[gameBoard.length-2][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                }
                // Top Row
                if (y==1) {
                    if (gameBoard[x-1][gameBoard[0].length-2]) { neighbor++; }
                    if (gameBoard[x][gameBoard[0].length-2]) { neighbor++; }
                    if (gameBoard[x+1][gameBoard[0].length-2]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }

                }
                // Bottom row
                if (y == gameBoard[0].length-2) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][1]) { neighbor++; }
                    if (gameBoard[x][1]) { neighbor++; }
                    if (gameBoard[x-1][1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }

                }
                // Right Column
                if (x == gameBoard.length-2) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[1][y-1]) { neighbor++; }
                    if (gameBoard[1][y]) { neighbor++; }
                    if (gameBoard[1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    }
                } else {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1,y-1));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x-1, y-1));
                            }
                        }
                    }
                }
            }
        }
        boardView.updateBoard(survivingCells);
    }

    // This will be called when simulate is called to run in a new thread
    public void run() {
        if (!isTorusModeOn) {
            advanceOneStep();
        } else { advanceOnStepTorus(); }
        try {
            // I think this value is the value that should be settable for jedi
            Thread.sleep(delayBetweenUpdates);
            run();
        } catch (InterruptedException ex) { }
    }

    private void checkTorusModeTopLeftCorner() {

    }
    private void checkTorusModeTopRightCorner() {

    }
    private void checkTorusModeBottomLeftCorner() {

    }
    private void checkTorusModeBottomRightCorner() {

    }
    private void checkTorusModeTopRow() {

    }
    private void checkTorusModeLeftColumn() {

    }

    private void checkTorusModeRightColumn() {

    }
    private void checkTorusModeBottomRow() {

    }

}
