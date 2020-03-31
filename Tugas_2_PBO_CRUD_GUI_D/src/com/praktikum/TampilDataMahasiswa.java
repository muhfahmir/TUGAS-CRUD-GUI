package com.praktikum;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class TampilDataMahasiswa extends JFrame {
    String[][] data = new String[500][3];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable tabel;
    JButton btnKembali = new JButton("Kembali");
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql://localhost/praktikum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    public TampilDataMahasiswa() {
        setTitle("Tampilkan Data Mahasiswa!");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl,
                    DBusername, DBpassword);
            statement = koneksi.createStatement();
            String sql = "select * from mahasiswa";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while (resultSet.next()) {
                data[p][0] = resultSet.getString("NIM");
                data[p][1] = resultSet.getString("Nama");
                data[p][2] = resultSet.getString("Alamat");
                p++;
            }
            statement.close();
            koneksi.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnKembali);
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

}
