package Martingale;

import javax.swing.*;
import java.awt.*;

public class Decorations {

    //applies the same style for input panels
    static void decoratePanels(JPanel... p){
        //System.out.println(p.length);
        for(int i=0; i<p.length; i++){
            p[i].setBackground(Color.WHITE);
        }
    }

    static void decorateTextLabels(JLabel... l){
        for(int i=0; i<l.length; i++){
            l[i].setFont(new Font("Sans Serif", Font.BOLD, 16));
        }
    }

    static void decorateTextFields(JTextField...t){
        for(int i=0; i<t.length; i++){
            t[i].setPreferredSize(new Dimension((int)(Main.frame_width*0.15F),
                    (int)(Main.frame_height*0.07F)));
            t[i].setFont(new Font("Sans Serif", Font.BOLD, 16));
        }
    }
    static void decorateButtons(JButton...b){
        for(int i=0; i<b.length; i++){
            b[i].setPreferredSize(new Dimension((int)(Main.frame_width*0.15F),
                    (int)(Main.frame_height*0.07F)));
            b[i].setFont(new Font("Sans Serif", Font.BOLD, 16));
            b[i].setRolloverEnabled(false);
        }
    }
}
