package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

    private JButton clear;
    private JButton advanceOneStep;
    private JButton simulate;
    private JButton autoGenerate;
    private JButton stopSimulate;

    public ButtonPanel() {
        clear = new JButton("Clear");
        advanceOneStep = new JButton("Advance");
        simulate = new JButton("Simulate");
        stopSimulate = new JButton("Stop Simulation");
        autoGenerate = new JButton("Auto Fill Board");
        add(clear);
        add(advanceOneStep);
        add(simulate);
        add(stopSimulate);
        add(autoGenerate);
    }

    public JButton getClear() {
        return clear;
    }
    public JButton getAdvanceOneStep(){
        return advanceOneStep;
    }
    public JButton getSimulate() {
        return simulate;
    }
    public JButton getStopSimulate() {
        return stopSimulate;
    }
    public JButton getAutoGenerate() {
        return autoGenerate;
    }

    public void addActionListenerForClear(ActionListener actionListener) {
        clear.addActionListener(actionListener);
    }

    public void addActionListenerForAdvance(ActionListener actionListener) {
        advanceOneStep.addActionListener(actionListener);
    }

    public void addActionListenerForSimulate(ActionListener actionListener) {
        simulate.addActionListener(actionListener);
    }

    public void addActionListenerForStopSimulate(ActionListener actionListener) {
        stopSimulate.addActionListener(actionListener);
    }

    public void addActionListenerForAutoGenerate(ActionListener actionListener) {
        autoGenerate.addActionListener(actionListener);
    }

}
