package com.praktikum;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewEditForm extends JFrame{

    JLabel lnama = new JLabel("Nama     :");
    JTextField tfnama = new JTextField();
    JLabel lnim = new JLabel("NIM      :");
    JTextField tfnim = new JTextField();
    JLabel lalamat = new JLabel("Alamat     :");
    JTextField tfalamat = new JTextField();

    JButton btnEdit = new JButton("Edit");
    JButton btnKembali = new JButton("Kembali");

    public ViewEditForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(800, 600);
        setBackground(Color.white);

        JLabel label = new JLabel("EDIT FORM");
        add(label);
        label.setBounds(350, 20, 100, 20);

        add(lnama);
        lnama.setBounds(250, 100, 90, 20);
        add(tfnama);
        tfnama.setBounds(350, 100, 120, 20);
        add(lnim);
        lnim.setBounds(250, 125, 90, 20);
        add(tfnim);
        tfnim.setBounds(350, 125, 120, 20);
        add(lalamat);
        lalamat.setBounds(250, 150, 90, 20);
        add(tfalamat);
        tfalamat.setBounds(350, 150, 120, 20);

        add(btnEdit);
        btnEdit.setBounds(250, 180, 90, 20);

        add(btnKembali);
        btnKembali.setBounds(350, 180, 90, 20);


    }

    public String getTfnama() {
        return tfnama.getText();
    }

    public String getTfnim() {
        return tfnim.getText();
    }

    public String getTfalamat() {
        return tfalamat.getText();
    }


}
