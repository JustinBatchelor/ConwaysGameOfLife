package Controller;

import Model.GameModel;
import View.CellConditionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CellConditionViewController implements ActionListener {

    private GameModel gameModel;
    private CellConditionsView cellConditionsPanel;

    public CellConditionViewController(GameModel model) {
        gameModel = model;
        cellConditionsPanel = new CellConditionsView();
        //               Making this VC the Listener For Everything
        //                      Survive Panel
        cellConditionsPanel.addActionListenerForSButton0(this);
        cellConditionsPanel.addActionListenerForSButton1(this);
        cellConditionsPanel.addActionListenerForSButton2(this);
        cellConditionsPanel.addActionListenerForSButton3(this);
        cellConditionsPanel.addActionListenerForSButton4(this);
        cellConditionsPanel.addActionListenerForSButton5(this);
        cellConditionsPanel.addActionListenerForSButton6(this);
        cellConditionsPanel.addActionListenerForSButton7(this);
        cellConditionsPanel.addActionListenerForSButton8(this);
        cellConditionsPanel.addActionListenerForClearSurviveList(this);
        cellConditionsPanel.addActionListenerForEnterButton(this);

        //                      Birth Panel
        cellConditionsPanel.addActionListenerForBButton0(this);
        cellConditionsPanel.addActionListenerForBButton1(this);
        cellConditionsPanel.addActionListenerForBButton2(this);
        cellConditionsPanel.addActionListenerForBButton3(this);
        cellConditionsPanel.addActionListenerForBButton4(this);
        cellConditionsPanel.addActionListenerForBButton5(this);
        cellConditionsPanel.addActionListenerForBButton6(this);
        cellConditionsPanel.addActionListenerForBButton7(this);
        cellConditionsPanel.addActionListenerForBButton8(this);
        cellConditionsPanel.addActionListenerForClearBirthList(this);
        cellConditionsPanel.addActionListenerForSetDefaultConditions(this);

    }

    public void actionPerformed(ActionEvent e) {
        //                    Tackle add Number Buttons First
        //                          Birth Buttons
        if (e.getSource() == cellConditionsPanel.getbButton0()) {
            cellConditionsPanel.addNumberToBirthList(0);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton1()) {
            cellConditionsPanel.addNumberToBirthList(1);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton2()) {
            cellConditionsPanel.addNumberToBirthList(2);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton3()) {
            cellConditionsPanel.addNumberToBirthList(3);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton4()) {
            cellConditionsPanel.addNumberToBirthList(4);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton5()) {
            cellConditionsPanel.addNumberToBirthList(5);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton6()) {
            cellConditionsPanel.addNumberToBirthList(6);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton7()) {
            cellConditionsPanel.addNumberToBirthList(7);
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getbButton8()) {
            cellConditionsPanel.addNumberToBirthList(8);
            cellConditionsPanel.updateBirthLabel();
        }
        //                        Survive Buttons
        if (e.getSource() == cellConditionsPanel.getsButton0()) {
            cellConditionsPanel.addNumberToSurviveList(0);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton1()) {
            cellConditionsPanel.addNumberToSurviveList(1);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton2()) {
            cellConditionsPanel.addNumberToSurviveList(2);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton3()) {
            cellConditionsPanel.addNumberToSurviveList(3);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton4()) {
            cellConditionsPanel.addNumberToSurviveList(4);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton5()) {
            cellConditionsPanel.addNumberToSurviveList(5);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton6()) {
            cellConditionsPanel.addNumberToSurviveList(6);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton7()) {
            cellConditionsPanel.addNumberToSurviveList(7);
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getsButton8()) {
            cellConditionsPanel.addNumberToSurviveList(8);
            cellConditionsPanel.updateSurviveLabel();
        }
        //                              Clear Buttons
        if (e.getSource() == cellConditionsPanel.getClearBirthListButton()) {
            cellConditionsPanel.clearBirthList();
            cellConditionsPanel.updateBirthLabel();
        }
        if (e.getSource() == cellConditionsPanel.getClearSurviveListButton()) {
            cellConditionsPanel.clearSurviveList();
            cellConditionsPanel.updateSurviveLabel();
        }
        if (e.getSource() == cellConditionsPanel.getEnterNewConditions()) {
            // Need To run some code here to get the birth and survive conditions proper
            List<Integer> listOfBirthConditions = new ArrayList<>(cellConditionsPanel.getBirthList());
            List<Integer> listOfSurviveConditions = new ArrayList<>(cellConditionsPanel.getSurviveList());

            // Should run some code to have another popup window to ensure the user wants those conditions
            cellConditionsPanel.clearBirthList();
            cellConditionsPanel.clearSurviveList();
            gameModel.clearBirthList();
            gameModel.clearSurviveList();
            gameModel.setCellIsBornConditions(listOfBirthConditions);
            gameModel.setCellLivesConditions(listOfSurviveConditions);
            cellConditionsPanel.dispose();
        }
        if (e.getSource() == cellConditionsPanel.getSetDefaultConditions()) {
            // run some code to do the same as above but for 2 & 3
            gameModel.setInitialSurviveConditions();
            gameModel.setInitialBornConditions();

            cellConditionsPanel.dispose();
        }

    }

    public CellConditionsView getCellConditionsView() {
        return cellConditionsPanel;
    }
}
