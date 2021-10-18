package Martingale;

import javax.swing.*;
import java.awt.*;

import static Martingale.SubPanelSwitcher.panelSwitchRouletteType;
import static Martingale.SubPanelUserInput.*;

public class PanelInput extends JFrame {

                /*  PanelInput Layout:
                panel_input contains:
                |SubPanelSwitcher -> row 1 panelSwitchRouletteType|
                |SubPanelUserInput -> panels(row 2 Cash, row 3 FirstBet, row 4 WinSum)|
                    */
    SubPanelUserInput subPanelUserInput;
    SubPanelSwitcher subPanelSwitcher;
    static JPanel panel_input = new JPanel();

    PanelInput(){

        subPanelUserInput = new SubPanelUserInput();
        subPanelSwitcher = new SubPanelSwitcher();
        panel_input.setBackground(Color.WHITE);
        panel_input.setLayout(new GridLayout(4, 1));
        panel_input.add(panelSwitchRouletteType);
        panel_input.add(panelCash);
        panel_input.add(panelFirstBet);
        panel_input.add(panelDesiredSum);
    }
}
