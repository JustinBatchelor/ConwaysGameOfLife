package View;

import javax.swing.*;
import java.awt.*;

public class InfoView extends JFrame {


    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;

    public InfoView() {
        setLayout(new GridLayout(20,1));
        label1 = new JLabel("Thank you for taking the time to play my version of Conways Game Of Life;");
        label2 = new JLabel("I wanted to inform you of all of the custom functionality ");
        label3 = new JLabel("I have implemented to make playing the game easier  ");
        label4 = new JLabel("Most of the functionality will be nested in the “Novice”, “Adpet”, and “Jedi” Tags;");

        label5 = new JLabel("There is a button panel located at the south of the view");
        label6 = new JLabel("This panel will allow you to advance the game by one step");
        label7 = new JLabel("or simulate the game on another thread with the  corresponding buttons");

        label8 = new JLabel("The clear button will reset the board to initial settings, it will also interrupt ");
        label9 = new JLabel("The current thread if it is in simulation, and then reset the board");

        label10 = new JLabel("When in Torus mode the color of the cells change from green to orange and vice versa");
        label11 = new JLabel("You may resize the board buy clicking any corner and expanding, or by maximizing the frame");
        label12 = new JLabel("(Or by the resize menu item)");

        label13 = new JLabel("You may add more than one cell by clicking and dragging");
        label14 = new JLabel("the mouse across as many cells as you wish. ");
        label15 = new JLabel("(You may also add cells while the game is in simulation)");
        label16 = new JLabel("Built by Justin Batchelor");

        label17 = new JLabel("");
        label18 = new JLabel("");
        label19 = new JLabel("");
        label20 = new JLabel("");

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label17);
        add(label5);
        add(label6);
        add(label7);
        add(label18);
        add(label8);
        add(label9);
        add(label19);
        add(label10);
        add(label11);
        add(label12);
        add(label20);
        add(label13);
        add(label14);
        add(label15);
        add(label16);
        setSize(700,400);
    }
}
