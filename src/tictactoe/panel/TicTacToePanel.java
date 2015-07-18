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

                        if(spaceToFill > 8) {
                            spaceToFill = smartComputer();
                        }

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
        ArrayList<Integer> spacesNotFilledByAnything = new ArrayList<>();

        for(int i = 0; i < buttons.length; i++) {
            if(buttons[i].getXoro() != XOrO.O) {
                spacesNotFilledByComputer++;

                if(buttons[i].getXoro() == null) {
                    spacesNotFilledByAnything.add(i);
                }
            } else if(buttons[i].getXoro() == XOrO.O) {
                spacesFilledByComputer.add(i);
            }
        }

        if(spacesNotFilledByComputer == 8) {
            noComputerFilledSpaces = true;
        }

        if (spacesFilledByComputer.size() > 0){
            for (int i = 0; i < buttons.length; i++) {
                if(spacesFilledByComputer.contains(i)) {
                    {
                        if (i == 1 || i == 4 || i == 7 ) {
                            if (spacesFilledByComputer.contains(i + 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i - 1);
                            }

                            if (spacesFilledByComputer.contains(i - 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i + 1);
                            }
                        }

                        if (i == 2 || i == 5 || i == 8) {
                            if (spacesFilledByComputer.contains(i - 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i - 2);
                            }
                        }

                        if (i == 0 || i == 3 || i == 6) {
                            if (spacesFilledByComputer.contains(i + 1)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                horizontalSpacesToFill.add(i + 1);
                            }
                        }
                    }

                    {
                        if (i == 0 || i == 1 || i == 2 ) {
                            if (spacesFilledByComputer.contains(i + 3)) {
                                hasAVerticalTwoSpaceToFill = true;
                                verticalSpacesToFill.add(i + 6);
                            }
                        }

                        if (i == 3 || i == 4 || i == 5) {
                            if (spacesFilledByComputer.contains(i - 3)) {
                                hasAVerticalTwoSpaceToFill = true;
                                verticalSpacesToFill.add(i + 3);
                            }

                            if (spacesFilledByComputer.contains(i + 3)) {
                                hasAHorizontalTwoSpaceToFill = true;
                                verticalSpacesToFill.add(i - 3);
                            }
                        }

                        if (i == 6 || i == 7 || i == 8) {
                            if (spacesFilledByComputer.contains(i - 3)) {
                                hasAVerticalTwoSpaceToFill = true;
                                verticalSpacesToFill.add(i - 6);
                            }
                        }
                    }

                    {
                        if (i == 0) {
                            if (spacesFilledByComputer.contains(i + 4)) {
                                hasADiagonalTwoSpaceToFill = true;
                                diagonalSpacesToFill.add(i + 8);
                            }
                        }

                        if (i == 2) {
                            if (spacesFilledByComputer.contains(i + 2)) {
                                hasADiagonalTwoSpaceToFill = true;
                                diagonalSpacesToFill.add(i + 4);
                            }
                        }

                        if (i == 6) {
                            if (spacesFilledByComputer.contains(i - 2)) {
                                hasADiagonalTwoSpaceToFill = true;
                                diagonalSpacesToFill.add(i - 4);
                            }
                        }

                        if (i == 8) {
                            if (spacesFilledByComputer.contains(i - 4)) {
                                hasADiagonalTwoSpaceToFill = true;
                                diagonalSpacesToFill.add(i - 8);
                            }
                        }
                    }
                }
            }
        }

        if(hasAHorizontalTwoSpaceToFill) {
            for(int i = 0; i < horizontalSpacesToFill.size(); i++) {
                if(buttons[horizontalSpacesToFill.get(i)].getXoro() == XOrO.X) {
                    horizontalSpacesToFill.remove(i);
                }
            }
        }

        if(hasADiagonalTwoSpaceToFill) {
            for(int i = 0; i < diagonalSpacesToFill.size(); i++) {
                if(buttons[diagonalSpacesToFill.get(i)].getXoro() == XOrO.X) {
                    diagonalSpacesToFill.remove(i);
                }
            }
        }

        if(hasAVerticalTwoSpaceToFill) {
            for(int i = 0; i < verticalSpacesToFill.size(); i++) {
                if(buttons[verticalSpacesToFill.get(i)].getXoro() == XOrO.X) {
                    verticalSpacesToFill.remove(i);
                }
            }
        }

        int randomVariable = 0;

        if (hasAHorizontalTwoSpaceToFill) {
            if (horizontalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * horizontalSpacesToFill.size());

                spaceToFill = horizontalSpacesToFill.get(randomVariable);

                System.out.println("Has a horizontal space");
            }
        } else if (hasAHorizontalTwoSpaceToFill && hasADiagonalTwoSpaceToFill) {
             if(horizontalSpacesToFill.size() > 0 && verticalSpacesToFill.size() > 0) {
                 randomVariable = (int) (Math.random() * 2 + 1);

                 if (randomVariable == 1) {
                     randomVariable = (int) (Math.random() * horizontalSpacesToFill.size());

                     spaceToFill = horizontalSpacesToFill.get(randomVariable);
                 } else if (randomVariable == 2) {
                     randomVariable = (int) (Math.random() * diagonalSpacesToFill.size());

                     spaceToFill = diagonalSpacesToFill.get(randomVariable);
                 }
             }
        } else if (hasAHorizontalTwoSpaceToFill && hasAVerticalTwoSpaceToFill) {
            if(horizontalSpacesToFill.size() > 0 && verticalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * 2 + 1);

                if (randomVariable == 1) {
                    randomVariable = (int) (Math.random() * horizontalSpacesToFill.size());

                    spaceToFill = horizontalSpacesToFill.get(randomVariable);
                } else if (randomVariable == 2) {
                    randomVariable = (int) (Math.random() * verticalSpacesToFill.size());

                    spaceToFill = verticalSpacesToFill.get(randomVariable);
                }
            }
        } else if (hasADiagonalTwoSpaceToFill) {
            if(diagonalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * diagonalSpacesToFill.size());

                spaceToFill = diagonalSpacesToFill.get(randomVariable);

                System.out.println("Has a diagonal space");
            }
        } else if (hasAVerticalTwoSpaceToFill) {
            if(verticalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * verticalSpacesToFill.size());

                spaceToFill = verticalSpacesToFill.get(randomVariable);

                System.out.println("Has a vertical space");
            }
        } if (hasADiagonalTwoSpaceToFill && hasAVerticalTwoSpaceToFill) {
            if(diagonalSpacesToFill.size() > 0 && verticalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * 2 + 1);

                if (randomVariable == 1) {
                    randomVariable = (int) (Math.random() * verticalSpacesToFill.size());

                    spaceToFill = verticalSpacesToFill.get(randomVariable);
                } else if (randomVariable == 2) {
                    randomVariable = (int) (Math.random() * diagonalSpacesToFill.size());

                    spaceToFill = diagonalSpacesToFill.get(randomVariable);
                }
            }
        } else if (hasADiagonalTwoSpaceToFill && hasAHorizontalTwoSpaceToFill && hasAVerticalTwoSpaceToFill) {
            if(diagonalSpacesToFill.size() > 0 && horizontalSpacesToFill.size() > 0 && verticalSpacesToFill.size() > 0) {
                randomVariable = (int) (Math.random() * 3 + 1);

                if (randomVariable == 1) {
                    randomVariable = (int) (Math.random() * verticalSpacesToFill.size());

                    spaceToFill = verticalSpacesToFill.get(randomVariable);
                } else if (randomVariable == 2) {
                    randomVariable = (int) (Math.random() * diagonalSpacesToFill.size());

                    spaceToFill = diagonalSpacesToFill.get(randomVariable);
                } else if (randomVariable == 3) {
                    randomVariable = (int) (Math.random() * horizontalSpacesToFill.size());

                    spaceToFill = horizontalSpacesToFill.get(randomVariable);
                }
            }
        } else if(buttons[4].getXoro() == null && noComputerFilledSpaces) {
            spaceToFill = 4;
        }

        if (!hasADiagonalTwoSpaceToFill && !hasAVerticalTwoSpaceToFill && !hasAHorizontalTwoSpaceToFill && buttons[4].getXoro() != null) {
            int emptySpaceToUse = (int) (Math.random() * spacesNotFilledByAnything.size());

            spaceToFill = spacesNotFilledByAnything.get(emptySpaceToUse);

            System.out.println("Using else");
        }

        return buttons[spaceToFill].getXoro() == null ? spaceToFill : spacesNotFilledByAnything.get(1);
    }

}