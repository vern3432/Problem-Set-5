package com.magicstore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MagicalItemShopGUI extends JFrame {
    private JLabel label;
    private JButton button;

    public MagicalItemShopGUI() {
        setTitle("Magical Item Shop");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Initialize components
        label = new JLabel("Welcome to William's Magical Item Shop!");
        label.setHorizontalAlignment(JLabel.CENTER); // Center the label text
        button = new JButton("Click Me");
        button.addActionListener(new ButtonClickListener());

        // Set layout and add components
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                label.setText("Button Clicked!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MagicalItemShopGUI gui = new MagicalItemShopGUI();
                gui.setVisible(true);
            }
        });
    }
}
