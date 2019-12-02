package Controller;

import Model.GameModel;
import View.ResizeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResizeViewController implements ActionListener {


    private ResizeView resizeView;
    private GameModel gameModel;

    public ResizeViewController(GameModel model) {
        gameModel = model;
        resizeView = new ResizeView();
        resizeView.addActionListenerForEnterButton(this);
    }

    public void actionPerformed(ActionEvent e) {
        String stringWidth = resizeView.getWidthTextField().getText();
        String stringHeight = resizeView.getHeightTextField().getText();
        if (stringHeight.equals("") || stringWidth.equals("")) {
            throw new IllegalArgumentException();
        }
        int width = Integer.parseInt(stringWidth);
        int height = Integer.parseInt(stringHeight);
        System.out.println(width + height);
        if (width > 500 || width < 10 || height > 500 || height < 10) {
            throw new IllegalArgumentException();
        } else {
            resizeView.dispose();
        }
    }

    public ResizeView getResizeView() {
        return resizeView;
    }
}
