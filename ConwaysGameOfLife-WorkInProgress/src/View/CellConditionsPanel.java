package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CellConditionsPanel extends JFrame{

    // Array to hold list of numbers
    List<Integer> surviveList;
    List<Integer> birthList;


    // Survive Panel
    private JButton sButton1;
    private JButton sButton2;
    private JButton sButton3;
    private JButton sButton4;
    private JButton sButton5;
    private JButton sButton6;
    private JButton sButton7;
    private JButton sButton8;
    private JLabel listOfSurviveConditions;
    private JButton clearSurviveListButton;


    // Birth Panel
    private JButton bButton1;
    private JButton bButton2;
    private JButton bButton3;
    private JButton bButton4;
    private JButton bButton5;
    private JButton bButton6;
    private JButton bButton7;
    private JButton bButton8;
    private JLabel listOfBirthConditions;
    private JButton clearBirthListButton;

    // components that are shared
    private JButton enterNewConditions;
    private JButton setDefaultConditions;


    public CellConditionsPanel() {
        //         Get The Buttons out of the way
        //              Survive Buttons
        sButton1 = new JButton("Add 1 to Survive Condition");
        sButton2 = new JButton("Add 2 to Survive Condition");
        sButton3 = new JButton("Add 3 to Survive Condition");
        sButton4 = new JButton("Add 4 to Survive Condition");
        sButton5 = new JButton("Add 5 to Survive Condition");
        sButton6 = new JButton("Add 6 to Survive Condition");
        sButton7 = new JButton("Add 7 to Survive Condition");
        sButton8 = new JButton("Add 8 to Survive Condition");
        clearSurviveListButton = new JButton("Clear Survive Selections");
        listOfSurviveConditions = new JLabel("Please select the survive conditions");

        //                 Birth Buttons
        bButton1 = new JButton("Add 1 to Birth Condition");
        bButton2 = new JButton("Add 2 to Birth Condition");
        bButton3 = new JButton("Add 3 to Birth Condition");
        bButton4 = new JButton("Add 4 to Birth Condition");
        bButton5 = new JButton("Add 5 to Birth Condition");
        bButton6 = new JButton("Add 6 to Birth Condition");
        bButton7 = new JButton("Add 7 to Birth Condition");
        bButton8 = new JButton("Add 8 to Birth Condition");
        clearBirthListButton = new JButton("Clear Birth Selections");
        listOfBirthConditions = new JLabel("Please select the birth conditions");

        //              General Buttons
        enterNewConditions = new JButton("Enter New Conditions");
        setDefaultConditions = new JButton("Set Conditions To Default");

        //                       Birth Panel Construction
        JPanel birthPanel = new JPanel(new GridLayout(11,1));
        birthPanel.add(listOfBirthConditions);
        birthPanel.add(bButton1);
        birthPanel.add(bButton2);
        birthPanel.add(bButton3);
        birthPanel.add(bButton4);
        birthPanel.add(bButton5);
        birthPanel.add(bButton6);
        birthPanel.add(bButton7);
        birthPanel.add(bButton8);
        birthPanel.add(clearBirthListButton);
        birthPanel.add(setDefaultConditions);

        //                  Survive Panel Construction
        JPanel survivePanel = new JPanel();
        survivePanel.add(listOfSurviveConditions);
        survivePanel.add(sButton1);
        survivePanel.add(sButton2);
        survivePanel.add(sButton3);
        survivePanel.add(sButton4);
        survivePanel.add(sButton5);
        survivePanel.add(sButton6);
        survivePanel.add(sButton7);
        survivePanel.add(sButton8);
        survivePanel.add(clearSurviveListButton);
        survivePanel.add(enterNewConditions);

        //                  Main Layout
        setLayout(new GridLayout(1,2));
        add(survivePanel);
        add(birthPanel);
        setSize(800,600);

    }

    //                                         Getters For Survive Buttons
    public JButton getsButton1() {
        return sButton1;
    }
    public JButton getsButton2() {
        return sButton2;
    }
    public JButton getsButton3() {
        return sButton3;
    }
    public JButton getsButton4() {
        return sButton4;
    }
    public JButton getsButton5() {
        return sButton5;
    }
    public JButton getsButton6() {
        return sButton6;
    }
    public JButton getsButton7() {
        return sButton7;
    }
    public JButton getsButton8() {
        return sButton8;
    }

    //                                         Getters For Birth Buttons
    public JButton getbButton1() {
        return bButton1;
    }
    public JButton getbButton2() {
        return bButton2;
    }
    public JButton getbButton3() {
        return bButton3;
    }
    public JButton getbButton4() {
        return bButton4;
    }
    public JButton getbButton5() {
        return bButton5;
    }
    public JButton getbButton6() {
        return bButton6;
    }
    public JButton getbButton7() {
        return bButton7;
    }
    public JButton getbButton8() {
        return bButton8;
    }


    public void addActionListenerForEnterButton(ActionListener actionListener) {

    }
    public void addActionListenerForSetDefaultConditions(ActionListener actionListener) {

    }
    public void addActionListenerForClearBirthList(ActionListener actionListener) {
        clearBirthListButton.addActionListener(actionListener);
    }
    public void addActionListenerForClearSurviveList(ActionListener actionListener) {
        clearSurviveListButton.addActionListener(actionListener);
    }


    //                          Action Listeners For each numbered Button in Survive Panel
    public void addActionListenerForSButton1(ActionListener actionListener) {
        sButton1.addActionListener(actionListener);
    }
    public void addActionListenerForSButton2(ActionListener actionListener) {
        sButton2.addActionListener(actionListener);
    }
    public void addActionListenerForSButton3(ActionListener actionListener) {
        sButton3.addActionListener(actionListener);
    }
    public void addActionListenerForSButton4(ActionListener actionListener) {
        sButton4.addActionListener(actionListener);
    }
    public void addActionListenerForSButton5(ActionListener actionListener) {
        sButton5.addActionListener(actionListener);
    }
    public void addActionListenerForSButton6(ActionListener actionListener) {
        sButton6.addActionListener(actionListener);
    }
    public void addActionListenerForSButton7(ActionListener actionListener) {
        sButton7.addActionListener(actionListener);
    }
    public void addActionListenerForSButton8(ActionListener actionListener) {
        sButton8.addActionListener(actionListener);
    }

//                              Action Listener For Each Numbered Button in Birth Panel
    public void addActionListenerForBButton1(ActionListener actionListener) {
        bButton1.addActionListener(actionListener);
    }
    public void addActionListenerForBButton2(ActionListener actionListener) {
        bButton2.addActionListener(actionListener);
    }
    public void addActionListenerForBButton3(ActionListener actionListener) {
        bButton3.addActionListener(actionListener);
    }
    public void addActionListenerForBButton4(ActionListener actionListener) {
        bButton4.addActionListener(actionListener);
    }
    public void addActionListenerForBButton5(ActionListener actionListener) {
        bButton5.addActionListener(actionListener);
    }
    public void addActionListenerForBButton6(ActionListener actionListener) {
        bButton6.addActionListener(actionListener);
    }
    public void addActionListenerForBButton7(ActionListener actionListener) {
        bButton7.addActionListener(actionListener);
    }
    public void addActionListenerForBButton8(ActionListener actionListener) {
        bButton8.addActionListener(actionListener);
    }


    public void addNumberToBirthList(int x) {
        if (birthList.contains(x)) { return; }
        else { birthList.add(x); }
    }

    public void addNumberToSurviveList(int x) {
        if (surviveList.contains(x)) {return;}
        else { surviveList.add(x); }
    }

    public void clearSurviveList() {
        if(!surviveList.isEmpty()) {
            surviveList.clear();
        }
    }
    public void clearBirthList(){
        if(!birthList.isEmpty()) {
            birthList.clear();
        }
    }

    // Call every time a button is pressed
    public void updateSurviveLabel() {
        if (surviveList.isEmpty()) {
            listOfSurviveConditions.setText("No Survive Conditions Set");
        } else {
           String appendToLabel = "";
            for(Integer i :surviveList) {
                String appendToString = (i + ", ");
                appendToLabel = appendToLabel + appendToString;
            }
            listOfSurviveConditions.setText("Current Survive Conditions are " + appendToLabel);
        }
    }

    // Call every time a button is pressed
    public void updateBirthLabel() {
        if (birthList.isEmpty()) {
            listOfBirthConditions.setText("No Birth Conditions Set");
        } else {
            String appendToLabel = "";
            for(Integer i :birthList) {
                String appendToString = (i + ", ");
                appendToLabel = appendToLabel + appendToString;
            }
            listOfBirthConditions.setText("Current Survive Conditions are " + appendToLabel);
        }
    }

    public List<Integer> getSurviveList() {
        return surviveList;
    }
    public List<Integer> getBirthList() {
        return birthList;
    }

}
