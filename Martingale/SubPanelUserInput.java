package Martingale;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Martingale.Decorations.*;

public class SubPanelUserInput extends JFrame implements ActionListener, CaretListener {

    static JPanel panelCash, panelDesiredSum, panelFirstBet; //panels containing buttons and fields
    static JButton buttonSubmitCash, buttonSubmitWinSum, buttonSubmitFirstBet; //buttons to submit input values
    static JTextField fieldCash, fieldWinSum, fieldFirstBet; //fields to where enter user values
    static JLabel textForCash, textForWin, textForBet; //annotations for input fields

    static long input_cash, input_bet, input_win; //to store inputted values
    static byte buttons_pressed_count = 0, number_of_buttons = 3;

    final String DESCRIPTION_CASH = "Your starting money (in $)",
    DESCRIPTION_WIN = "You want to win (in $)",
    DESCRIPTION_BET = "Your first bet (in $)";
    final String CASH = "1000", WIN = "10000", BET = "5"; //default values
    final String ERROR_MESSAGE = "Please, enter a positive integer number", ERROR_MESSAGE_TITLE = "Input error";
    final String SUBMITTED = "Done!";

    String default_button_text = "Submit";
    Color default_button_color, default_foreground_color;
    static Emulate emulate;// Emulate is a subclass of SwingWorker for background running roulette emulation
    static boolean already_running = false;

    SubPanelUserInput() {
        textForCash = new JLabel(DESCRIPTION_CASH);
        textForWin = new JLabel(DESCRIPTION_WIN);
        textForBet = new JLabel(DESCRIPTION_BET);

        //text fields for 2-4th rows in PanelInput
        fieldCash = new JTextField(CASH);
        fieldWinSum = new JTextField(WIN);
        fieldFirstBet = new JTextField(BET);

        //submit buttons for 2-4th row
        buttonSubmitCash = new JButton(default_button_text);
        buttonSubmitWinSum = new JButton(default_button_text);
        buttonSubmitFirstBet = new JButton(default_button_text);

        //2nd row
        panelCash = new JPanel(new GridLayout(0, 3));
        panelCash.add(textForCash);
        panelCash.add(fieldCash);
        panelCash.add(buttonSubmitCash);
        //3rd row
        panelDesiredSum = new JPanel(new GridLayout(0, 3));
        panelDesiredSum.add(textForWin);
        panelDesiredSum.add(fieldWinSum);
        panelDesiredSum.add(buttonSubmitWinSum);
        //4th row
        panelFirstBet = new JPanel(new GridLayout(0, 3));
        panelFirstBet.add(textForBet);
        panelFirstBet.add(fieldFirstBet);
        panelFirstBet.add(buttonSubmitFirstBet);

        //methods from class Decorations
        decoratePanels(panelCash, panelDesiredSum, panelFirstBet);
        decorateTextLabels(textForBet, textForCash, textForWin);
        decorateTextFields(fieldFirstBet, fieldWinSum, fieldCash);
        decorateButtons(buttonSubmitCash, buttonSubmitWinSum, buttonSubmitFirstBet);

        buttonSubmitCash.addActionListener(this);
        buttonSubmitWinSum.addActionListener(this);
        buttonSubmitFirstBet.addActionListener(this);
        /* calculation starts only when all 3 buttons are pressed
         * pressed buttons are visually changed
         */

        //obtain buttons defaults to restore changed ones later
        getButtonDefaults(buttonSubmitCash, fieldCash);

        fieldCash.addCaretListener(this);
        fieldFirstBet.addCaretListener(this);
        fieldWinSum.addCaretListener(this);
    }

    void getButtonDefaults(JButton b, JTextField t) {
        default_button_text = b.getText();
        default_button_color = b.getBackground();
        default_foreground_color = t.getForeground();
    }

    void setButtonPressed(JButton b, JTextField t) {
        if (default_button_text.equals(b.getText())){
            b.setBackground(Color.red);
            b.setText(SUBMITTED);
            t.setForeground(Color.red);
            buttons_pressed_count++;
        }
    }

    void setButtonDefault(JButton b, JTextField t) {
        if(!default_button_text.equals(b.getText())) {
            buttons_pressed_count--;
            b.setBackground(default_button_color);
            b.setText(default_button_text);
            t.setForeground(default_foreground_color);
        }
    }
    //fires error on wrong input (if not a number or not integer)
    void fireError(JButton b, JTextField f){
        JOptionPane.showMessageDialog(panelCash, ERROR_MESSAGE,
                ERROR_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
        setButtonDefault(b, f);
    }

    void submitValues(){
        if (input_cash < input_bet){
            setButtonDefault(buttonSubmitCash, fieldCash);
            setButtonDefault(buttonSubmitFirstBet, fieldFirstBet);
            JOptionPane.showMessageDialog(panelCash, "Not enough cash to make a bet",
                    "Wrong input", JOptionPane.ERROR_MESSAGE);
            //not needed, was added for testing
            System.out.println(buttons_pressed_count);
        }
        else if (input_cash >= input_win){
            setButtonDefault(buttonSubmitCash, fieldCash);
            setButtonDefault(buttonSubmitWinSum, fieldWinSum);
            JOptionPane.showMessageDialog(panelCash, "Your cash is already greater than/equal to your win",
                    "Wrong input", JOptionPane.ERROR_MESSAGE);
            //not needed, was added for testing
            System.out.println(buttons_pressed_count);
        }
        else {
            if (!already_running)
                already_running = true;
            else{
                if(!emulate.isDone())
                    emulate.cancel(true);
            }
            //calculations are running in the background -> GUI not freezes
            emulate = new Emulate(input_cash, input_bet, input_win);
            emulate.execute();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmitCash) {
            try {
                input_cash = Long.parseLong(fieldCash.getText());
                if (input_cash <= 0) {
                    fireError(buttonSubmitCash, fieldCash);
                }
                else {
                    setButtonPressed(buttonSubmitCash, fieldCash);
                    if (buttons_pressed_count >= number_of_buttons)
                        submitValues();
                }
            }
            catch (NumberFormatException ex) {
                fireError(buttonSubmitCash, fieldCash);
            }
        }

        if (e.getSource() == buttonSubmitWinSum) {
            try {
                input_win = Long.parseLong(fieldWinSum.getText());
                if (input_win <= 0) {
                    fireError(buttonSubmitWinSum, fieldWinSum);
                }
                else {
                    setButtonPressed(buttonSubmitWinSum, fieldWinSum);
                    if (buttons_pressed_count >= number_of_buttons)
                        submitValues();
                }
            }
            catch (NumberFormatException ex) {
                fireError(buttonSubmitWinSum, fieldWinSum);
            }
        }

        if (e.getSource() == buttonSubmitFirstBet) {
            try {
                input_bet = Long.parseLong(fieldFirstBet.getText());
                if (input_bet <= 0) {
                    fireError(buttonSubmitFirstBet, fieldFirstBet);
                }
                else {
                    setButtonPressed(buttonSubmitFirstBet, fieldFirstBet);
                    if (buttons_pressed_count >= number_of_buttons)
                        submitValues();
                }
            }
            catch (NumberFormatException ex) {
                fireError(buttonSubmitFirstBet, fieldFirstBet);
            }
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if(e.getSource() == fieldCash){
            setButtonDefault(buttonSubmitCash, fieldCash);
        }

        if(e.getSource() == fieldWinSum){
            setButtonDefault(buttonSubmitWinSum, fieldWinSum);
        }

        if(e.getSource() == fieldFirstBet){
            setButtonDefault(buttonSubmitFirstBet, fieldFirstBet);
        }
    }
}