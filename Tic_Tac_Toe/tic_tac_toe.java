package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tic_tac_toe implements ActionListener {
    JFrame jf = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel("Tic-tac-toe");
    JButton[] buttons = new JButton[9];
    boolean player1Turn = true;

    tic_tac_toe() {
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 800);
        jf.getContentPane().setBackground(new Color(50, 50, 50));
        jf.setLayout(new BorderLayout());
        jf.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("", Font.BOLD, 55));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 110));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        jf.add(titlePanel, BorderLayout.NORTH);
        jf.add(buttonPanel);

        firstTurn();
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            if (player1Turn) {
                clickedButton.setForeground(new Color(255, 0, 0));
                clickedButton.setText("X");
            } else {
                clickedButton.setForeground(new Color(0, 0, 255));
                clickedButton.setText("O");
            }
            player1Turn = !player1Turn;
            textField.setText(player1Turn ? "X's turn" : "O's turn");
            check();
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player1Turn = Math.random() < 0.5; // Randomly set the first player's turn
        textField.setText(player1Turn ? "X's turn" : "O's turn");
    }

    public void check() {
        String[] playerSymbols = {"X", "O"};

        for (String playerSymbol : playerSymbols) {
            for (int[] combination : new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}) {
                int a = combination[0];
                int b = combination[1];
                int c = combination[2];

                if (buttons[a].getText().equals(playerSymbol) && buttons[b].getText().equals(playerSymbol) && buttons[c].getText().equals(playerSymbol)) {
                    win(playerSymbol, a, b, c);
                    return; // Exit the method as we have a winner
                }
            }
        }

        // Check for a draw
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            textField.setText("It's a draw!");
        }
    }

    public void win(String playerSymbol, int a, int b, int c) {
        buttons[a].setBackground(new Color(0, 255, 0));
        buttons[b].setBackground(new Color(0, 255, 0));
        buttons[c].setBackground(new Color(0, 255, 0));
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText(playerSymbol + " Wins!");
    }}