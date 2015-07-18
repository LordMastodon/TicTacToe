package tictactoe.panel;

import net.miginfocom.swing.MigLayout;
import tictactoe.TicTacToe;
import tictactoe.logic.Turn;
import tictactoe.logic.XOrO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TicTacToePanel extends ImagePanel {

    XOButton[] buttons = new XOButton[9];

    Turn turn = Turn.PLAYER_TURN;

    public static boolean xWin = false;
    public static boolean oWin = false;

    ActionListener buttonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(!xWin && !oWin) {
                gameLogicPlayerTurn(e.getActionCommand());
            }
        }
    };

    public TicTacToePanel(BufferedImage img, ImageObserver obv) {
        super(img, obv);

        MigLayout layout = new MigLayout(
                "wrap 3",
                "[298]3[298]3[298]",
                "[298]3[298]3[298]"
        );

        setLayout(layout);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new XOButton();

            buttons[i].setActionCommand("" + i);

            buttons[i].addActionListener(buttonListener);
            buttons[i].setFont(new Font("quartz", Font.PLAIN, 100));

            buttons[i].setContentAreaFilled(false);
            buttons[i].setRolloverEnabled(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorderPainted(false);

            buttons[i].setPreferredSize(new Dimension(298, 298));

            add(buttons[i]);
        }

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                if(!xWin && !oWin) {
                    if (turn == Turn.COMPUTER_TURN) {
                        int spaceToFill = smartComputer();

                        if (buttons[spaceToFill].getXoro() == null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            registerButtonClick(spaceToFill, XOrO.O);

                            for (int i = 0; i < buttons.length; i++) {
                                if (buttons[i].getXoro() == null) {
                                    buttons[i].addActionListener(buttonListener);
                                }
                            }

                            turn = Turn.PLAYER_TURN;
                        }
                    }
                }
            }
        }, 0, 10);

        t.schedule(new TimerTask() {
            public void run() {
                if(!xWin && !oWin) {
                    {
                        if (buttons[0].getXoro() == XOrO.X && buttons[3].getXoro() == XOrO.X && buttons[6].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[0].getXoro() == XOrO.X && buttons[1].getXoro() == XOrO.X && buttons[2].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[0].getXoro() == XOrO.X && buttons[4].getXoro() == XOrO.X && buttons[8].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[1].getXoro() == XOrO.X && buttons[4].getXoro() == XOrO.X && buttons[7].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[2].getXoro() == XOrO.X && buttons[5].getXoro() == XOrO.X && buttons[8].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[2].getXoro() == XOrO.X && buttons[4].getXoro() == XOrO.X && buttons[6].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[6].getXoro() == XOrO.X && buttons[7].getXoro() == XOrO.X && buttons[8].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        } else if (buttons[3].getXoro() == XOrO.X && buttons[4].getXoro() == XOrO.X && buttons[5].getXoro() == XOrO.X) {
                            xWin = true;

                            System.out.println("X won!");
                        }
                    }

                    {
                        if (buttons[0].getXoro() == XOrO.O && buttons[3].getXoro() == XOrO.O && buttons[6].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[0].getXoro() == XOrO.O && buttons[1].getXoro() == XOrO.O && buttons[2].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[0].getXoro() == XOrO.O && buttons[4].getXoro() == XOrO.O && buttons[8].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[1].getXoro() == XOrO.O && buttons[4].getXoro() == XOrO.O && buttons[7].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[2].getXoro() == XOrO.O && buttons[5].getXoro() == XOrO.O && buttons[8].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[2].getXoro() == XOrO.O && buttons[4].getXoro() == XOrO.O && buttons[6].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[6].getXoro() == XOrO.O && buttons[7].getXoro() == XOrO.O && buttons[8].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        } else if (buttons[3].getXoro() == XOrO.O && buttons[4].getXoro() == XOrO.O && buttons[5].getXoro() == XOrO.O) {
                            oWin = true;

                            System.out.println("O won!");
                        }
                    }
                }
            }
        }, 0, 10);
    }

    public void registerButtonClick(int i, XOrO xoro) {
        if(xoro.equals(xoro.X)) {
            buttons[i].setXoro(XOrO.X);
        } else {
            buttons[i].setXoro(XOrO.O);
        }
    }

    public void gameLogicPlayerTurn(String actionCommand) {
        registerButtonClick(Integer.parseInt(actionCommand), XOrO.X);

        turn = Turn.COMPUTER_TURN;

        for(int i = 0; i < buttons.length; i++) {
            buttons[i].removeActionListener(buttonListener);
        }
    }

    public int smartComputer() {
        int spaceToFill = 0;

        boolean noComputerFilledSpaces = true;

        int spacesNotFilledByComputer = 0;

        boolean hasADiagonalTwoSpaceToFill = false;
        boolean hasAHorizontalTwoSpaceToFill = false;
        boolean hasAVerticalTwoSpaceToFill = false;

        ArrayList<Integer> diagonalSpacesToFill = new ArrayList<>();
        ArrayList<Integer> horizontalSpacesToFill = new ArrayList<>();
        ArrayList<Integer> verticalSpacesToFill = new ArrayList<>();

        ArrayList<Integer> spacesFilledByComputer = new ArrayList<>();

        for(int i = 0; i < buttons.length; i++) {
            if(buttons[i].getXoro() != XOrO.O) {
                spacesNotFilledByComputer++;
            } else if(buttons[i].getXoro() == XOrO.O) {
                spacesFilledByComputer.add(i);
            }
        }

        if(spacesNotFilledByComputer == 8) {
            noComputerFilledSpaces = true;
        }

        if(buttons[4].getXoro() == null && noComputerFilledSpaces) {
            spaceToFill = 4;
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if(spacesFilledByComputer.contains(i)) {
                    if (i == 1 || i == 4 || i == 7 ) {
                        {
                            if (spacesFilledByComputer.contains(i + 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i - 1);
                            }

                            if (spacesFilledByComputer.contains(i - 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i + 1);
                            }
                        }
                    }

                    if (i == 2 || i == 5 || i == 8) {
                        {
                            if (spacesFilledByComputer.contains(i - 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i - 2);
                            }
                        }
                    }

                    if (i == 0 || i == 3 || i == 6) {
                        {
                            if (spacesFilledByComputer.contains(i + 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i + 1);
                            }
                        }
                    }
                }
            }
        }

        if(hasAHorizontalTwoSpaceToFill) {
            for(int i = 0; i < horizontalSpacesToFill.size(); i++) {
                if(buttons[i].getXoro() != null) {
                    horizontalSpacesToFill.remove(i);
                }
            }
        }

        int i = 0;

        return spaceToFill;
    }

}