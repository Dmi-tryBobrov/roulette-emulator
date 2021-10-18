package Martingale;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static Martingale.SubPanelUserInput.panelCash;
import static java.lang.System.exit;

public class MenuBar extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu fileMenu, helpMenu;
    JMenuItem saveItem, exitItem, helpItem;
    HelpWindow helpWindow;

    public JMenuBar mainMenu(){

        menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
                saveItem = new JMenuItem("Save");
                exitItem = new JMenuItem("Exit");
            helpMenu = new JMenu("Help");
                helpItem = new JMenuItem("Help");

            fileMenu.add(saveItem);
            fileMenu.add(exitItem);
            helpMenu.add(helpItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

            fileMenu.addActionListener(this);
                exitItem.addActionListener(this);
                saveItem.addActionListener(this);
            helpMenu.addActionListener(this);
                helpItem.addActionListener(this);

        setKeyBindings();
        return menuBar;
    }

    private void setKeyBindings(){
            fileMenu.setMnemonic(KeyEvent.VK_F);
            helpMenu.setMnemonic(KeyEvent.VK_H);
                exitItem.setMnemonic(KeyEvent.VK_X);
                saveItem.setMnemonic(KeyEvent.VK_S);
                helpItem.setMnemonic(KeyEvent.VK_H);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem)
            exit(0);
        if(e.getSource() == saveItem) {
            //can safely save output in .txt files
            CustomFileChooser fileChooser = new CustomFileChooser("txt");
            //default filename for saving output
            fileChooser.setSelectedFile(new File("listing.txt"));
            if (fileChooser.showSaveDialog(panelCash) == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile().getAbsolutePath());
                    writer.println(ListingWindow.textArea.getText());
                    writer.close();
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                }
        }
        if(e.getSource() == helpItem)
            helpWindow = new HelpWindow();
    }

    private static class CustomFileChooser extends JFileChooser {
        private final String extension;
        public CustomFileChooser(String extension) {
            super();
            this.extension = extension;
        }

        @Override public File getSelectedFile() {
            File selectedFile = super.getSelectedFile();

            if (selectedFile != null) {
                String name = selectedFile.getName();
                if (!name.contains("."))
                    selectedFile = new File(selectedFile.getParentFile(),
                            name + '.' + extension);
            }

            return selectedFile;
        }

        @Override public void approveSelection() {
            if (getDialogType() == SAVE_DIALOG) {
                File selectedFile = getSelectedFile();
                if ((selectedFile != null) && selectedFile.exists()) {
                    int response = JOptionPane.showConfirmDialog(this,
                            "The file " + selectedFile.getName() +
                                    " already exists. Do you want to replace the existing file?",
                            "Overwrite file", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (response != JOptionPane.YES_OPTION)
                        return;
                }
            }

            super.approveSelection();
        }
    }
}


