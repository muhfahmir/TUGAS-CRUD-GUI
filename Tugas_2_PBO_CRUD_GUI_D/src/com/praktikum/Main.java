package com.praktikum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        new Menu();
    }
}

class Menu extends JFrame implements ActionListener{
    JLabel ljudul;
    JButton btnInput,btnHapus,btnEdit,btnTampil,btnExit;

    public Menu(){
        ljudul = new JLabel("MENU");
        btnInput = new JButton("1. Input Data Mahasiswa");
        btnTampil = new JButton("2. Tampilkan Seluruh Data");
        btnHapus = new JButton("3. Hapus Data Mahasiswa");
        btnEdit = new JButton("4. Edit Data Mahasiswa");
        btnExit = new JButton("0. Exit");

        btnInput.addActionListener(this);
        btnTampil.addActionListener(this);
        btnHapus.addActionListener(this);
        btnEdit.addActionListener(this);
        btnExit.addActionListener(this);


        setLayout(null);
        add(ljudul);
        add(btnExit);
        add(btnEdit);
        add(btnHapus);
        add(btnTampil);
        add(btnInput);


        ljudul.setBounds(70,10,200,25);
        btnInput.setBounds(70,40,200,25);
        btnTampil.setBounds(70,70,200,25);
        btnHapus.setBounds(70,100,200,25);
        btnEdit.setBounds(70,130,200,25);
        btnExit.setBounds(70,160,200,25);

        setTitle("GUI MAHASISWA");
        setSize(350,280);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        btnInput.setHorizontalAlignment(SwingConstants.LEFT);
        btnTampil.setHorizontalAlignment(SwingConstants.LEFT);
        btnHapus.setHorizontalAlignment(SwingConstants.LEFT);
        btnEdit.setHorizontalAlignment(SwingConstants.LEFT);
        btnExit.setHorizontalAlignment(SwingConstants.LEFT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnInput){
            new InputDataMahasiswa();
            setVisible(false);

        }else if (e.getSource()==btnTampil){
            new TampilDataMahasiswa();
            setVisible(false);

        }else if (e.getSource()==btnHapus){
            new HapusDataMahasiswa();
            setVisible(false);

        }else if(e.getSource()==btnEdit){
            new EditDataMahasiswa();
            setVisible(false);

        }
        else if (e.getSource()==btnExit){
            // kelas exit
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Apakah anda akan menutup Aplikasi?", "Tutup Aplikasi", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}