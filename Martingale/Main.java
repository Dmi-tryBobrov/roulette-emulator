package Martingale;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main extends JFrame {

    //User window dimensions for relative sizes
    public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int WIDTH = gd.getDisplayMode().getWidth();
    public static final int HEIGHT = gd.getDisplayMode().getHeight();

    static final String TITLE = "Martingale Roulette Emulator"; //DEFAULT TITLE
    static Color DEFAULT_COLOR = Color.lightGray;
    public static int frame_width, frame_height;
    public static ImageIcon window_image;
    public static String frame_title;
    //system dependent GUI
    private static boolean system_look_and_feel = true;

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(HEIGHT*60/100, WIDTH*60/100, TITLE);
            }
        });
   }

    private static void createAndShowGUI(int user_x, int user_y, String user_title) {

        frame_height = user_y;
        frame_width = user_x;
        frame_title = user_title;

        if(system_look_and_feel) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JFrame mainFrame = new JFrame();
        mainFrame.setLocation(0,0);

        //sets FrameIcon(header)
        window_image = new ImageIcon(Main.class.getResource("roulette.jpg"));
        mainFrame.setIconImage(window_image.getImage());

        //code for mainFrame general layout
        mainFrame.setTitle(frame_title);
        mainFrame.setSize(frame_width, frame_height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setBackground(DEFAULT_COLOR);

        //instantiate MenuBar member which creates main menu above user actions panel
        mainFrame.setJMenuBar(new MenuBar().mainMenu());

    /*mainframe consists of one gridbaglayout panel (for user actions)
    mainFrame panel consists of four sub-panels:
    |________________________________MenuBar_____________________________________|
    1) for user input -> panelInput    ||  2) to contain image -> panelImage
    3) for output -> panelOutput       ||  4) for listing button -> panelListing  */

        MainFrameLayout mainFrameLayout = new MainFrameLayout();
        mainFrame.add(mainFrameLayout);

        //Display the window
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }
}
