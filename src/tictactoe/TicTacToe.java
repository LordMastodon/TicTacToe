package tictactoe;

import tictactoe.panel.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TicTacToe extends JFrame {

    void addComponentsToPane() throws IOException {
        BufferedImage backgroundImage = ImageIO.read(new File("res/Overlay.png"));

        TicTacToePanel panel = new TicTacToePanel(backgroundImage, this);

        setContentPane(panel);
    }

    static void createAndShowGui() {
        TicTacToe main = new TicTacToe("TicTacToe");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        main.setSize(900, 900);

        try {
            main.addComponentsToPane();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        main.setLocation((screenSize.width / 2) - 450, (screenSize.height / 2) - 450);

        main.setResizable(false);
        main.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    public TicTacToe(String name) {
        super(name);
    }

}
