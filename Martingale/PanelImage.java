package Martingale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelImage extends JPanel {

    static JPanel panel_image = new JPanel();
    static Image scaled_image;

    PanelImage(){
        panel_image.setLayout(new BorderLayout());
        panel_image.setBackground(Color.WHITE);

        BufferedImage image = null;
        try{
            image = ImageIO.read(Main.class.getResource("casino.jpg"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        scaled_image = image.getScaledInstance(Main.HEIGHT*30/100, Main.HEIGHT*30/100, Image.SCALE_SMOOTH);
        JLabel image_label = new JLabel (new ImageIcon(scaled_image));
        image_label.setVerticalAlignment(JLabel.CENTER);
        image_label.setHorizontalAlignment(JLabel.CENTER);

        panel_image.add(image_label);
    }

}
