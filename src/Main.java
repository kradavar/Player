//import controller.Controller;
import view.PlayerGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        /*Controller controller = new Controller();
        controller.start();
*/
        JFrame TitanFrame = new JFrame();
        TitanFrame.setPreferredSize(new Dimension(1000, 600));
        TitanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerGUI TitanGUI = new PlayerGUI();
        TitanGUI.createPlayer(TitanFrame);
        TitanGUI.createMenu(TitanFrame);
        TitanFrame.pack();
        TitanFrame.setVisible(true);

    }
}
