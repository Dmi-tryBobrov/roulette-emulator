package Martingale;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class HelpWindow {

    JDialog helpDialog = new JDialog();
    static JTextArea textHelp = new JTextArea();

    HelpWindow() {

        helpDialog.setLayout(new BorderLayout());
        helpDialog.setTitle("Help");
        helpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        textHelp.setLineWrap(true);
        textHelp.setWrapStyleWord(true);
        textHelp.setEditable(false);
        textHelp.setFont(new Font("Sans Serif", Font.BOLD, 16));

        String content = null;
        try {
            content = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textHelp.setText(content);

        JScrollPane helpPanel = new JScrollPane(textHelp);
        helpPanel.setPreferredSize(new Dimension(Main.WIDTH/2,Main.HEIGHT/2));

        helpDialog.add(helpPanel);
        helpDialog.setVisible(true);
        textHelp.setCaretPosition(0);
        helpDialog.pack();
    }
    private String readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = Main.class.getResourceAsStream("help.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        return sb.toString();
    }
}
