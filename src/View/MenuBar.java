package View;

import javax.swing.*;

public class MenuBar {

    private JMenuBar menuBar;
    private JMenu basics, adept, jedi;
    private JMenuItem clear, autoGenerate, resize;
    private JMenuItem torus, cellConditions;
    private JMenuItem treadUpdateTime;

    public MenuBar() {
        // Menu Bar
        menuBar = new JMenuBar();

        // Menu tags
        basics = new JMenu("Basics");
        adept = new JMenu("Adept");
        jedi = new JMenu("Jedi");

        //menu items

    }
}
