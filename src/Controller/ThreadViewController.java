package Controller;

import Model.GameModel;
import View.ThreadView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadViewController implements ActionListener {

    private ThreadView threadView;
    private GameModel gameModel;

    public ThreadViewController(GameModel model) {
        threadView = new ThreadView();
        gameModel = model;
        threadView.addActionListenerForEnterButton(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == threadView.getEnterButton()) {
            String typedNumber = threadView.getNumberOfMilliseconds().getText();
            Integer convertText = Integer.parseInt(typedNumber);
            gameModel.setDelayBetweenUpdates(convertText);
            threadView.dispose();
        }
    }

    public ThreadView getThreadView() {
        return threadView;
    }
}
