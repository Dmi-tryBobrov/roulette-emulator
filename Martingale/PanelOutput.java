package Martingale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Martingale.Decorations.decorateButtons;

public class PanelOutput extends JFrame implements ActionListener {

    static JButton buttonRetry;
    static JTextField fieldResults;
    static JPanel panelForResults;
    static JPanel panel_output = new JPanel();
    static String buttonRetryText = "Retry";
    Timer timer;

    PanelOutput(){
        panel_output.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel_output.setBackground(Color.WHITE);

        panelForResults = new JPanel(new BorderLayout());
        panelForResults.setBackground(Color.WHITE);
        fieldResults = new JTextField();
        fieldResults.setFont(new Font("Sans Serif", Font.BOLD, 14));
        fieldResults.setHorizontalAlignment(JTextField.LEADING);

        panelForResults.add(fieldResults);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.9;
        c.anchor = GridBagConstraints.PAGE_START;
        panel_output.add(panelForResults, c);

        buttonRetry = new JButton(buttonRetryText);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;
        panel_output.add(buttonRetry, c);

        decorateButtons(buttonRetry);
        buttonRetry.addActionListener(this);

        ActionListener activateCancel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonRetry.setText("Cancel");
                fieldResults.setText("Wait...");
            }
        };
        timer = new Timer(2000, activateCancel);
        timer.setRepeats(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonRetry){
            /*retry button doesn't react if not all data was submitted
             * when all data is submitted all buttons in input field
             * must be pressed
             */
            if(SubPanelUserInput.buttons_pressed_count >= SubPanelUserInput.number_of_buttons) {
                //cancel lengthy calculations and reset panel to default
                if("Cancel".equals(buttonRetry.getText())){
                    buttonRetry.setText(buttonRetryText);
                    SubPanelUserInput.emulate.cancel(true);
                    fieldResults.setText("Cancelled");
                }
                else {
                    //starts calculation in Emulate background thread
                    SubPanelUserInput.emulate = new Emulate(SubPanelUserInput.input_cash,
                            SubPanelUserInput.input_bet,
                            SubPanelUserInput.input_win);
                    SubPanelUserInput.emulate.execute();
                }
            }

        }

    }
}
