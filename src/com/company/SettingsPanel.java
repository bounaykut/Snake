package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    JLabel fpsLabel;
    JLabel speedLabel;

    ButtonGroup qualityButtons;
    JRadioButton low,medium,high;

    JSlider speedSlider;

    public SettingsPanel(){

        setLayout(new GridBagLayout());

        fpsLabel = new JLabel("Quality:");

        //buttons/////

        qualityButtons = new ButtonGroup();

        low = new JRadioButton("Low");
        medium = new JRadioButton("Medium");
        medium.setSelected(true);
        high = new JRadioButton("High");
        qualityButtons.add(low);
        qualityButtons.add(medium);
        qualityButtons.add(high);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getActionCommand()){
                    case "Low":
                        GamePanel.FPS = 30;
                        break;
                    case "Medium":
                        GamePanel.FPS = 45;
                        break;
                    case "High":
                        GamePanel.FPS = 60;
                        break;
                    default:
                        break;
                }
            }
        };

        low.addActionListener(listener);
        medium.addActionListener(listener);
        high.addActionListener(listener);
        /////////////

        speedLabel = new JLabel("Game speed:");

        speedSlider = new JSlider(5,30);

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GamePanel.SPEED_FACTOR = speedSlider.getValue();
            }
        });


        GridBagConstraints c = new GridBagConstraints();


        //quality label
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        add(fpsLabel,c);

        //radiobuttons
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        add(low,c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        add(medium,c);

        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        add(high,c);
        //

        //speed label
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.1;
        add(speedLabel,c);

        //speed slider
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        add(speedSlider,c);

        setFocusable(true);
    }


}
