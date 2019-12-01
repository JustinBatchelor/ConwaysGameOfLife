package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {


    private JButton advanceOneStep;
    private JButton simulate;
    private JButton stopSimulate;

    public ButtonPanel() {
        advanceOneStep = new JButton("Advance");
        simulate = new JButton("Simulate");
        stopSimulate = new JButton("Stop Simulation");
        add(advanceOneStep);
        add(simulate);
        add(stopSimulate);
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

    public void addActionListenerForAdvance(ActionListener actionListener) {
        advanceOneStep.addActionListener(actionListener);
    }

    public void addActionListenerForSimulate(ActionListener actionListener) {
        simulate.addActionListener(actionListener);
    }

    public void addActionListenerForStopSimulate(ActionListener actionListener) {
        stopSimulate.addActionListener(actionListener);
    }

}
