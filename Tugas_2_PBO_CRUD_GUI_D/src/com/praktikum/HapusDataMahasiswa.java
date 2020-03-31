package com.praktikum;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
public class HapusDataMahasiswa extends JFrame {

    String nim;
    String[][] data = new String[500][3];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable tabel;
    JButton btnKembali = new JButton("Kembali");
    JButton btnHapus = new JButton("Hapus");
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql://localhost/praktikum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    int bar,kol;
    String dataterpilih=null;
    public HapusDataMahasiswa() {
        setTitle("Hapus Data Mahasiswa!");
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
        add(btnHapus);
        btnHapus.setEnabled(false);
        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                bar = tabel.getSelectedRow();
                kol = tabel.getSelectedColumn();

                dataterpilih = tabel.getValueAt(bar, 0).toString();
                System.out.println(dataterpilih);
                if (dataterpilih != null) {
                    btnHapus.setEnabled(true);
                }
            }

        });


        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    nim=dataterpilih;
                    String query = "DELETE FROM `mahasiswa` WHERE `nim` = '"+nim+"'";
                    Class.forName("com.mysql.jdbc.Driver");
                    koneksi = DriverManager.getConnection(DBurl,
                            DBusername, DBpassword);
                    statement = koneksi.createStatement();
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
                }catch(SQLException sql){
                    System.out.println(sql.getMessage());
                }catch (ClassNotFoundException sql){
                    System.out.println(sql.getMessage());
                }
                setVisible(false);
                new HapusDataMahasiswa();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

}

