package Martingale;

import javax.swing.*;
import java.awt.*;

public class MainFrameLayout extends JPanel {

    PanelListing panelListing;
    PanelInput panelInput;
    PanelImage panelImage;
    PanelOutput panelOutput;
    private final Color DEFAULT_COLOR = Color.lightGray;

    MainFrameLayout(){
        /*mainframe consists of one gridbaglayout panel (for user actions)
    mainFrame panel consists of four sub-panels:
    |________________________________MenuBar_____________________________________|
    1) for user input -> panelInput    ||  2) to contain image -> panelImage
    3) for output -> panelOutput       ||  4) for listing button -> panelListing  */

        GridBagConstraints input = new GridBagConstraints();
        GridBagConstraints image = new GridBagConstraints();
        GridBagConstraints output = new GridBagConstraints();
        GridBagConstraints listing = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        panelInput = new PanelInput();
        input.fill = GridBagConstraints.BOTH;
        input.gridx = 0;
        input.gridy = 0;
        input.ipadx = 40;
        this.add(PanelInput.panel_input, input);

        panelImage = new PanelImage();
        image.fill = GridBagConstraints.BOTH;
        image.gridx = 1;
        image.gridy = 0;
        this.add(PanelImage.panel_image, image);

        panelOutput = new PanelOutput();
        output.fill = GridBagConstraints.BOTH;
        output.gridx = 0;
        output.gridy = 1;
        this.add(PanelOutput.panel_output, output);

        panelListing = new PanelListing();
        listing.fill = GridBagConstraints.BOTH;
        listing.gridx = 1;
        listing.gridy = 1;

        this.add(PanelListing.panel_listing, listing);
        //this.fillPanelsByDefault();
    }

    private void fillPanelsByDefault(){
        PanelInput.panel_input.setBackground(DEFAULT_COLOR);
        PanelImage.panel_image.setBackground(DEFAULT_COLOR);
        PanelOutput.panel_output.setBackground(DEFAULT_COLOR);
        PanelListing.panel_listing.setBackground(DEFAULT_COLOR);
    }
}
