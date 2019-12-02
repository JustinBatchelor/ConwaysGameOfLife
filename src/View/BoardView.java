package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
/*
 * need a way to update the board size based on the model data
 * need to attach listeners to each cell that is created by the paintComponent.
 * since my board will be resizeable the documentation says I need to use
 *
 * public void componentResized(ComponentEvent e) {}
 * public void componentMoved(ComponentEvent e) {}
 * public void componentShown(ComponentEvent e) {}
 * public void componentHidden(ComponentEvent e) {}  (I think???)
 *
 * I also need to add the mouse listeners so the view can tell the controller if the user clicks a cell
 * however I dont think I really need the "spot entered" and "Spot exited" listeners...
 *
 * but how are you going to pass this data after running it on a thread in the model? Or should I run it
 * in the controller?
 *
 * Should I keep a reference to the model list of points and just have an update method?
 *
 */
public class BoardView extends JPanel {

    private int x;
    private int y;
    private static final int cellSize = 10;

    private boolean torusMode;

    private static Dimension gameBoardDimensions;
    private ArrayList<Point> cells;

    public BoardView(){
        gameBoardDimensions = null;
        cells = new ArrayList<>();
        torusMode = false;
    }

    public void setTorusMode(boolean bool) {
        torusMode = bool;
    }

    public Dimension getGameBoardDimensions() {
        return gameBoardDimensions;
    }

    public void setDimensions(Dimension dimensions){
        gameBoardDimensions = dimensions;
    }

    public int getCellSize() {
        return cellSize;
    }

    public ArrayList<Point> getCells() {
        return cells;
    }

//                               Methods to update the board based on the model

    // needed for when the board is resized (this will probably also be used in the model)
    // (maybe call when the listener detects change in controller)
    public void updateBoardSize() {
        ArrayList<Point> removeCells = new ArrayList<>();
        for (Point p : cells) {
            if ((p.x > gameBoardDimensions.width-1) || (p.y > gameBoardDimensions.height-1)) {
                removeCells.add(p);
            }
        }
        cells.removeAll(removeCells);
        repaint();
    }

    public void resetBoard(){
        cells.clear();
        updateBoardSize();
    }

    public void removePoint(int x, int y) {
        cells.remove(new Point(x,y));
    }

    // call after simulate or advance is pressed
    public void updateBoard(ArrayList<Point> survivingCells) {
        resetBoard();
        cells = survivingCells;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            for(Point p : cells) {
                if (torusMode) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(cellSize + (cellSize * p.x), cellSize + (cellSize * p.y), cellSize, cellSize);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillRect(cellSize + (cellSize * p.x), cellSize + (cellSize * p.y), cellSize, cellSize);
                }
            }
        } catch (ConcurrentModificationException e) {}
        g.setColor(Color.BLACK);
        for (x = 0; x <= gameBoardDimensions.getWidth(); x++) {
            g.drawLine(((x * cellSize) + cellSize), cellSize, (x * cellSize) + cellSize, cellSize + (cellSize * gameBoardDimensions.height));
        }
        for (y = 0; y <= gameBoardDimensions.getHeight(); y++) {
            g.drawLine(cellSize, ((y * cellSize) + cellSize), cellSize * (gameBoardDimensions.width), ((y * cellSize) + cellSize));
        }


    }


    // Listeners I am going to add to the controller
    public void addComponentListenerToController(ComponentListener componentListener) {
        this.addComponentListener(componentListener);
    }

    public void addMouseListenerToController(MouseListener mouseListener) {
        this.addMouseListener(mouseListener);
    }

    public void addMouseMotionListenerToController(MouseMotionListener mouseMotionListener) {
        this.addMouseMotionListener(mouseMotionListener);
    }





}
