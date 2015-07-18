package tictactoe.panel;

import tictactoe.logic.XOrO;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class XOButton extends JButton {

    XOrO xoro;

    public void setXoro(XOrO newXoro) {
        xoro = newXoro;
    }

    public XOrO getXoro() {
        return xoro;
    }

    public XOButton() {
        Timer t = new Timer();

        t.schedule(new TimerTask() {
            public void run() {
                if (xoro == XOrO.X) {
                    setText("X");
                } else if (xoro == XOrO.O) {
                    setText("O");
                } else {

                }
            }
        }, 0, 5);
    }

}
