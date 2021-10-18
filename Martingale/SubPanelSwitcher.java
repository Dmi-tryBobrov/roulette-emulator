package Martingale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Martingale.Decorations.decoratePanels;
import static Martingale.Decorations.decorateTextLabels;

public class SubPanelSwitcher extends JFrame implements ActionListener {

    static JPanel panelWithButtons, panelSwitchRouletteType;
    static JRadioButton radioButtonEurope, radioButtonUS; //buttons for roulette type -> 1 or 2 zeroes
    static JLabel textForSwitcher;
    final static String[] rouletteTypes = {"European (1 zero)", "American (2 zeroes)"};
    final static String switcherAnnotation = "Choose roulette type";

    SubPanelSwitcher(){
        textForSwitcher = new JLabel(switcherAnnotation);

        //sets buttons for switcher
        radioButtonEurope = new JRadioButton(rouletteTypes[0]);
        radioButtonEurope.setSelected(true); //is selected by default
        radioButtonEurope.setBackground(Color.WHITE);
        radioButtonUS = new JRadioButton(rouletteTypes[1]);
        radioButtonUS.setBackground(Color.WHITE);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonEurope);
        buttonGroup.add(radioButtonUS);
        panelWithButtons = new JPanel(new GridLayout(1, 2));
        panelWithButtons.add(radioButtonEurope);
        panelWithButtons.add(radioButtonUS);

        panelSwitchRouletteType = new JPanel(new GridLayout(2,1));
        panelSwitchRouletteType.add(textForSwitcher);
        panelSwitchRouletteType.add(panelWithButtons);

        decoratePanels(panelSwitchRouletteType);
        decorateTextLabels(textForSwitcher);

        radioButtonUS.addActionListener(this);
        radioButtonEurope.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == radioButtonUS) {
            System.out.println("American");//not needed, was added for testing
            Emulate.WIN_PROB = 18.0 / 38.0; //American style roulette has 2 zeros
        }

        if (e.getSource() == radioButtonEurope) {
            System.out.println("Europe");//not needed, was added for testing
            Emulate.WIN_PROB = 18.0 / 37.0; //European style roulette has 1 zero && 18 + 18 black&reds
        }

    }
}
