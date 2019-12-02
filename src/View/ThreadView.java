package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThreadView extends JFrame{

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField numberOfMilliseconds;
    private JButton enterButton;

    public ThreadView() {
        setLayout(new GridLayout(3,1));
        setSize(600,300);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3,1));
        label1 = new JLabel("Enter the number of milliseconds between updates");
        label2 = new JLabel("Enter 1000 for 1 second between updates");
        label3 = new JLabel("Enter a number as low as 10 milliseconds");
        textPanel.add(label1);
        textPanel.add(label2);
        textPanel.add(label3);

        JPanel userInteractionPanel = new JPanel();
        userInteractionPanel.setLayout(new GridLayout(1,1));
        numberOfMilliseconds = new JTextField();
        enterButton = new JButton("Enter");
        numberOfMilliseconds.setText("Number between 10-1000");
        userInteractionPanel.add(numberOfMilliseconds);

        add(textPanel);
        add(userInteractionPanel);
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
