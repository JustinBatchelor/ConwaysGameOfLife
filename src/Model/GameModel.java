package Model;


import View.BoardView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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


    public GameModel(BoardView view) {
        boardView = view;

    }

    public void randomlyFillBoard() {
        for (x=0; x< boardView.getGameBoardDimensions().width; x++) {
            for (y=0; y< boardView.getGameBoardDimensions().height; y++) {
                if (Math.random()*100 < .2) {
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
                    if ((neighbor == 2) || (neighbor == 3)) {
                        survivingCells.add(new Point(x - 1, y - 1));
                    }
                } else {
                    if (neighbor == 3) {
                        survivingCells.add(new Point(x - 1, y - 1));
                    }
                }
            }
        }
        boardView.updateBoard(survivingCells);
    }


    // This will be called when simulate is called to run in a new thread
    public void run() {
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
                    if ((neighbor == 2) || (neighbor == 3)) {
                        survivingCells.add(new Point(x - 1, y - 1));
                    }
                } else {
                    if (neighbor == 3) {
                        survivingCells.add(new Point(x - 1, y - 1));
                    }
                }
            }
        }
        boardView.updateBoard(survivingCells);
        try {
            // I think this value is the value that should be settable for jedi
            Thread.sleep(300);
            run();
        } catch (InterruptedException ex) {
        }
    }



}
