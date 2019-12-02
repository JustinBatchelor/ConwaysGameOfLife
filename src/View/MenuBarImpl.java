package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBarImpl extends JPanel{

    private JMenuBar menuBar;
    private JMenu basics, adept, jedi;
    private JMenuItem clear, autoGenerate, resize;
    private JMenuItem torus, cellConditions;
    private JMenuItem threadUpdateTime;

    public MenuBarImpl() {
        // Menu Bar
        menuBar = new JMenuBar();

        // Menu tags
        basics = new JMenu("Basics");
        adept = new JMenu("Adept");
        jedi = new JMenu("Jedi");

        //menu items
        clear = new JMenuItem("Clear");
        autoGenerate = new JMenuItem("AutoFill");
        resize = new JMenuItem("Resize");
        torus = new JMenuItem("Torus Mode");
        cellConditions = new JMenuItem("Set Cell Conditions");
        threadUpdateTime = new JMenuItem("Thead Update Timer");

        add(menuBar);

        // Add tags to bar
        menuBar.add(basics);
        menuBar.add(adept);
        menuBar.add(jedi);

        // Add items to tags
        basics.add(clear);
        basics.add(autoGenerate);
        basics.add(resize);

        adept.add(torus);
        adept.add(cellConditions);

        jedi.add(threadUpdateTime);
        setVisible(true);
    }

    public void addActionListenerForClear(ActionListener actionListener) { clear.addActionListener(actionListener); }
    public void addActionListenerForAutoGenerate(ActionListener actionListener) { autoGenerate.addActionListener(actionListener); }
    public void addActionListenerForResize(ActionListener actionListener) { resize.addActionListener(actionListener); }
    public void addActionListenerForTorus(ActionListener actionListener) { torus.addActionListener(actionListener); }
    public void addActionListenerForCellConditions(ActionListener actionListener) { cellConditions.addActionListener(actionListener); }
    public void addActionListenerForThread(ActionListener actionListener) { threadUpdateTime.addActionListener(actionListener); }

    public JMenuItem getClear() {
        return clear;
    }
    public JMenuItem getAutoGenerate() {
        return autoGenerate;
    }
    public JMenuItem getResize() {
        return resize;
    }
    public JMenuItem getCellConditions() {
        return cellConditions;
    }
    public JMenuItem getTorus() {
        return torus;
    }
    public JMenuItem getThreadUpdateTime() {
        return threadUpdateTime;
    }




}
