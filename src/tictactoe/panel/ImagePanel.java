package tictactoe.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

public class ImagePanel extends JPanel {

    BufferedImage img;
    ImageObserver obsv;

    boolean xHasWon = false;
    boolean oHasWon = false;

    public ImagePanel(BufferedImage newImg, ImageObserver obv) {
        img = newImg;

        obsv = obv;
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.drawImage(img, 0, 0, obsv);
    }

}
