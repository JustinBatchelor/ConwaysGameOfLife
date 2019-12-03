package Model;


import View.BoardView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
        if (passedSecondsFromController > 1000) {
            delayBetweenUpdates = 1000;
        }
        if (passedSecondsFromController < 10) {
            delayBetweenUpdates = 10;
        } else { delayBetweenUpdates = passedSecondsFromController; }
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
            gameBoard[p.x][p.y] = true;
        }
        ArrayList<Point> survivingCells = new ArrayList<>();
        for (x = 0; x < gameBoard.length - 2; x++) {
            for (y = 0; y < gameBoard[0].length - 2; y++) {
                int neighbor = 0;
                // Top Left Corner
                if (x == 0 && y == 0) {
                    // need to check diagonal corner
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[x+1][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][y]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][y+1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                }
                // BottomLeft corner
                else if (x == 0 && y == gameBoard[0].length - 3) {
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][0]) {neighbor++;}
                    if (gameBoard[0][0]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][0]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][y-1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                }
                // Top Right corner
                else if (x == gameBoard.length - 3 && y == 0) {
                    if (gameBoard[0][0]) { neighbor++; }
                    if (gameBoard[0][1]) { neighbor++; }
                    if (gameBoard[0][gameBoard[0].length-3]) { neighbor++; }
                    if (gameBoard[gameBoard.length-3][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[gameBoard.length-4][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[x-1][0]) {neighbor++;}
                    if (gameBoard[x-1][y+1]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][1]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                }
                // Bot Right corner
                else if (x==gameBoard.length - 3 && y == gameBoard[0].length - 3) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[0][0]) {neighbor++;}
                    if (gameBoard[0][gameBoard[0].length-3]) {neighbor++;}
                    if (gameBoard[0][gameBoard[0].length-4]) {neighbor++;}
                    if (gameBoard[gameBoard.length-3][0]) {neighbor++;}
                    if (gameBoard[gameBoard.length-4][0]) {neighbor++;}
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                }
                // Left Column
                else if (x==0) {
                    if (gameBoard[gameBoard.length-3][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[gameBoard.length-3][y+1]) { neighbor++; }
                    if (gameBoard[gameBoard.length-3][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                }
                // Top Row
                else if (y==0) {
                    if (gameBoard[x-1][gameBoard[0].length-3]) { neighbor++; }
                    if (gameBoard[x][gameBoard[0].length-3]) { neighbor++; }
                    if (gameBoard[x+1][gameBoard[0].length-3]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }

                }
                // Bottom row
                else if (y == gameBoard[0].length-3) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y-1]) { neighbor++; }
                    if (gameBoard[x+1][y]) { neighbor++; }
                    if (gameBoard[x+1][0]) { neighbor++; }
                    if (gameBoard[x][0]) { neighbor++; }
                    if (gameBoard[x-1][0]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }

                }
                // Right Column
                else if (x == gameBoard.length-3) {
                    if (gameBoard[x-1][y-1]) { neighbor++; }
                    if (gameBoard[x][y-1]) { neighbor++; }
                    if (gameBoard[0][y-1]) { neighbor++; }
                    if (gameBoard[0][y]) { neighbor++; }
                    if (gameBoard[0][y+1]) { neighbor++; }
                    if (gameBoard[x][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y+1]) { neighbor++; }
                    if (gameBoard[x-1][y]) { neighbor++; }
                    if (gameBoard[x][y]) {
                        for (Integer i : cellLivesConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    }
                } else if (x > 0 && x<gameBoard.length-2 && y > 0 && y<gameBoard[0].length-2) {
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
                                survivingCells.add(new Point(x,y));
                            }
                        }
                    } else {
                        for (Integer i : cellIsBornConditions) {
                            if (neighbor == i) {
                                survivingCells.add(new Point(x,y));
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

}
