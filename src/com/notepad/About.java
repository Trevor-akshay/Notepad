package com.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    Button button;
    About(){

        setBounds(500,200,600,600);
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Windows_10-Logo.png"));
        Image image = icon.getImage().getScaledInstance(400,200,Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel  label = new JLabel(icon1);
        label.setBounds(100,40,400,100);

        add(label);

        JLabel label1 = new JLabel("Notepad implementation using Java - Trevor_akshay");
        label1.setBounds(70,30,500,300);
        label1.setFont(new Font("SEN_SERIF",Font.PLAIN,20));
        add(label1);

        button = new Button("OK");
        button.setBounds(260,500,80,25);
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }
}
