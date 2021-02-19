package com.notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    String text;

    Notepad() {
        /*Default constraints for window*/
        setVisible(true);
        setBounds(0, 0, 1900, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NotePad - Trevor_akshay");
        // this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);

        //Menubar for editing , opening .txt files

        var menuBar = new JMenuBar();
        var file = new JMenu("File");

        var newFile = new JMenuItem("New");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newFile.addActionListener(this);

        var open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        var save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        var exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);


        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(exit);

        var edit = new JMenu("Edit");

        var copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        var paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        var cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        var selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);


        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        var about = new JMenu("About");

        var aboutMe = new JMenuItem("About Me");
        aboutMe.addActionListener(this);


        about.add(aboutMe);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(about);
        setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        textArea.setBounds(0, 0, 1900, 1000);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            textArea.setText(" ");

        } else if (e.getActionCommand().equals("Open")) {
            JFileChooser open = new JFileChooser();
            open.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("please select any .txt file", "txt");
            open.setApproveButtonText("Save");
            int action = open.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION)
                return;

            File file = open.getSelectedFile();

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                textArea.read(bufferedReader, null);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION)
                return;

            File file = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Copy")) {
            text = textArea.getSelectedText();
        } else if (e.getActionCommand().equals("Paste")) {
            textArea.insert(text, textArea.getCaretPosition());
        } else if (e.getActionCommand().equals("Cut")) {
            text = textArea.getSelectedText();
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        } else if (e.getActionCommand().equals("Select All")) {
            textArea.selectAll();
        } else if (e.getActionCommand().equals("About Me")) {
            new About().setVisible(true);
        }
    }
}
