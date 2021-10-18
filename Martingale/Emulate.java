package Martingale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.LinkedList;

public class Emulate extends SwingWorker<Void, Void> {

    long cash, bet, win;
    long lost_rolls_count = 0, rolls_played = 0, current_roll = 0, waged_money = 0;

    String result; //after each turn the result is generated
    static double WIN_PROB = 18.0/37.0; //default European style roulette with 1 zero
    static long ROLLS_MAX = 101; //First and last {ROLLS_MAX - 1} of rolls to display
    LinkedList<String> list_output_first = new LinkedList<>(); //stores first {ROLLS_MAX - 1} rolls
    LinkedList<String> list_output_last = new LinkedList<>(); //stores last {ROLLS_MAX - 1} rolls

    //timer for changes to occur if calculations are lengthy
    Timer timer;
    int timer_delay = 200;


    Emulate(long Cash, long Bet, long Win){
        //clears window with last listing and text field with last results
        PanelOutput.fieldResults.setText("");
        ListingWindow.textArea.setText("");
        cash = Cash;
        bet = Bet;
        win = Win;
    }

    void generateOutput() {
        if (rolls_played < ROLLS_MAX) {
            list_output_first.add(result);
        }
        else if (rolls_played < ROLLS_MAX*2){
            list_output_last.add(result);
        }
        else {
            //resets last entries
            list_output_last.pop();
            list_output_last.add(result);
        }
    }

    void sendOutput() {
        timer.stop();
        /*when running buttonRetry label is "Cancel"
         *sets to default when calculation is over
         */
        PanelOutput.buttonRetry.setText(PanelOutput.buttonRetryText);
        while(!list_output_first.isEmpty()) {
            ListingWindow.textArea.append(list_output_first.pop());
        }
        while (!list_output_last.isEmpty())
            ListingWindow.textArea.append(list_output_last.pop());
    }

    @Override
    protected Void doInBackground() throws Exception {
        //action performed when timer is up
        ActionListener activateCancel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelOutput.buttonRetry.setText("Cancel");
                PanelOutput.fieldResults.setText("Wait...Current sum: " + cash +
                        "; Rolls played: " + rolls_played);
            }
        };
        timer = new Timer(timer_delay, activateCancel);
        timer.setDelay(2000);//update every 2 sec
        timer.start();

        String explain_msg;
        /* one cannot double the last bet if one doesn't have enough money
         * while this flag is false - player can use martingale method
         */
        boolean new_martingale_flag = false;
        long current_bet = 0;

        while (cash < win){
            if(current_roll==0) {
                current_bet = bet;
            }
            else{
                current_bet *= 2;
            }

            if(current_bet <= cash) {
                current_roll++;
                rolls_played++;
                cash -= current_bet;
                waged_money += current_bet;
                if(new_martingale_flag){
                    explain_msg = "Cannot continue martingale, start again\n\n";
                    new_martingale_flag = false;
                }
                else
                    explain_msg = "";
            }
            //if not enough cash to continue this roll, but is possible to start once again
            else if(cash >= bet) {
                new_martingale_flag = true;
                current_roll = 0;
                continue;
            }
            else{
                break;
            }

            //win or lose this gamble
            if(Math.random() < WIN_PROB){
                result = MessageFormat.format(
                        "{0}Roll #{1};\tLost spin(s) in row: {2};\t" +
                                "Current bet:{3};\tCash on hand: {4};\t" +
                                "Already waged:{5};\tWon! Total: {6}\n\n",
                        explain_msg, rolls_played, current_roll - 1, current_bet,
                        cash, waged_money,  cash + waged_money + bet);
                this.generateOutput();

                cash = cash + waged_money + bet;
                current_roll = 0;
                waged_money = 0;
            }
            else {
                result = MessageFormat.format(
                        "{0}Roll #{1};\tLost spin(s) in row: {2};\t" +
                                "Current bet: {3};\tCash on hand: {4};\t" +
                                "Already waged: {5};\tLost:( Total: {6}\n\n",
                        explain_msg, rolls_played, current_roll - 1, current_bet,
                        cash, waged_money, cash);
                this.generateOutput();

                lost_rolls_count++;
            }
        }
        return null;
    }

    @Override
    public void done(){
        if (cash >= win){
            PanelOutput.fieldResults.setText("You won! Cash: " + cash + "$; " + "Rolls played: " +
                    rolls_played + "; Rolls lost: " + lost_rolls_count);
            ListingWindow.textArea.setText("You won! Cash: " + cash + "$; " + "Rolls played: " +
                    rolls_played + "; Rolls lost: " + lost_rolls_count + "\n");
            this.sendOutput();
            //for QA purposes
            System.out.printf("You won, your cash: %d; # of rolls played -> %d, #of rolls lost -> %d, %f\n",
                    cash, rolls_played, lost_rolls_count, (double)lost_rolls_count/rolls_played);
        }
        else{
            PanelOutput.fieldResults.setText("You Lost:( Cash: " + cash + "$; " + "Rolls played: " +
                    rolls_played + "; Rolls lost: " + lost_rolls_count);
            ListingWindow.textArea.setText("You Lost:( Cash: " + cash + "$; " + "Rolls played: " +
                    rolls_played + "; Rolls lost: " + lost_rolls_count + "\n");
            this.sendOutput();
            //for QA purposes
            System.out.printf("You lost, your cash: %d; # of rolls played -> %d, #of rolls lost -> %d, %f\n",
                    cash, rolls_played, lost_rolls_count, (double)lost_rolls_count/rolls_played);
        }
    }
}