package Martingale;

import javax.swing.*;
import java.awt.*;

public class ListingWindow{

    JDialog listingDialog = new JDialog();
    JPanel listingPanel = new JPanel(new FlowLayout());
    static JTextArea textArea = new JTextArea(20, 70);

    ListingWindow(){

        listingDialog.setLayout(new BorderLayout());
        listingDialog.setTitle("Listing");
        listingDialog.add(listingPanel);
        listingDialog.setResizable(true);

        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Sans Serif", Font.BOLD, 14));
        textArea.setLineWrap(false);
        textArea.setTabSize(4);

        listingPanel.setOpaque(true);
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize((new Dimension(Main.WIDTH/2, Main.HEIGHT/2)));
        listingPanel.add(scroller);

        listingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        listingDialog.setVisible(true);
        listingDialog.pack();
    }
}
