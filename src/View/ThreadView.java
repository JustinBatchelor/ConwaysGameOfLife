package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThreadView extends JFrame{

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    private JTextField numberOfMilliseconds;
    private JButton enterButton;

    public ThreadView() {
        setLayout(new GridLayout(7,1));
        setSize(600,300);
        label1 = new JLabel("Enter the number of milliseconds between updates");
        label2 = new JLabel("Enter 1000 for 1 second between updates");
        label3 = new JLabel("Enter a number as low as 10 milliseconds");
        label4 = new JLabel("Any number grater than 1000 will be auto set to 1000");
        label5 = new JLabel("Any number less than 10 will be auto set to 10");
        numberOfMilliseconds = new JTextField();
        enterButton = new JButton("Enter");
        numberOfMilliseconds.setText("Number between 10-1000");
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(numberOfMilliseconds);
        add(enterButton);
    }

    public void addActionListenerForEnterButton(ActionListener actionListener) {
        enterButton.addActionListener(actionListener);
    }

    public JTextField getNumberOfMilliseconds() {
        return numberOfMilliseconds;
    }

    public JButton getEnterButton() {
        return enterButton;
    }
}
