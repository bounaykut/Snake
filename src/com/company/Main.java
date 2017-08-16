package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    //buttons in navigation panel
    private JButton playButton;
    private JButton settingsButton;

    //those panels will be added to cards
    private GamePanel gamePanel;
    private SettingsPanel settingsPanel;

    //add those to frame
    JPanel cards;
    JPanel navigationPanel;


    public Main(){

        gamePanel = new GamePanel();
        settingsPanel = new SettingsPanel();

        cards = new JPanel(new CardLayout());
        cards.add(gamePanel,"play");
        cards.add(settingsPanel,"settings");



        navigationPanel = new JPanel(new FlowLayout());

        settingsButton = new JButton("settings");
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "settings");
            }
        });
        playButton = new JButton("play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "play");
                new Thread(){
                    @Override
                    public void run() {
                        gamePanel.requestFocusInWindow();
                        gamePanel.play();
                    }
                }.start();
            }
        });
        navigationPanel.add(settingsButton);
        navigationPanel.add(playButton);


        add(navigationPanel, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);
        setSize(GamePanel.WIDTH,GamePanel.LENGTH);
        setVisible(true);

    }


    public static void main(String[] args) {

        Main mainFrame = new Main();

    }
}
