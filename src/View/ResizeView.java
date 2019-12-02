package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResizeView extends JFrame {

    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel resizeNote;
    private JLabel dragLabel;
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JButton enterButton;

    public ResizeView() {
        setLayout(new GridLayout(3,1));
        setSize(600,200);
        JPanel dimensionsLayout = new JPanel();
        JPanel hintLayout = new JPanel();
        hintLayout.setLayout(new GridLayout(2,1));
        dimensionsLayout.setLayout(new GridLayout(2,2));
        widthLabel = new JLabel("Width");
        heightLabel = new JLabel("Height");
        widthTextField = new JTextField();
        heightTextField = new JTextField();
        widthTextField.setText("Enter Width");
        heightTextField.setText("Enter Height");
        resizeNote = new JLabel("(You may also resize the board by dragging any corner)");
        dragLabel = new JLabel("(To highlight many cells you may click and drag)");
        enterButton = new JButton("Enter");

        dimensionsLayout.add(widthLabel);
        dimensionsLayout.add(widthTextField);
        dimensionsLayout.add(heightLabel);
        dimensionsLayout.add(heightTextField);

        hintLayout.add(resizeNote);
        hintLayout.add(dragLabel);

        add(dimensionsLayout);
        add(hintLayout);
        add(enterButton);


    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public JTextField getHeightTextField() {
        return heightTextField;
    }

    public JTextField getWidthTextField() {
        return widthTextField;
    }

    public void addActionListenerForEnterButton(ActionListener actionListener){
        enterButton.addActionListener(actionListener);
    }



}
