package Martingale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Martingale.Decorations.decorateButtons;

public class PanelListing extends JFrame implements ActionListener {

    static JButton buttonListing;
    static JPanel panel_listing = new JPanel();
    ListingWindow listingWindow;

    PanelListing(){
        panel_listing.setBackground(Color.WHITE);
        panel_listing.setLayout(new BorderLayout());
        buttonListing = new JButton("Listing");
        decorateButtons(buttonListing);
        panel_listing.add(buttonListing);

        buttonListing.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonListing){
            listingWindow = new ListingWindow();
        }

    }
}
