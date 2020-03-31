package com.praktikum;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
public class EditDataMahasiswa extends JFrame {
    ViewEditForm viewEditForm;
    String nama,nim,alamat;
    String[][] data = new String[500][3];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable tabel;
    JButton btnKembali = new JButton("Kembali");
    JButton btnEdit = new JButton("Edit");
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql://localhost/praktikum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    int bar,kol;
    String dataterpilih=null;
    public EditDataMahasiswa() {
        setTitle("Edit Data Mahasiswa!");
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
        add(btnEdit);
        btnEdit.setEnabled(false);
        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                bar = tabel.getSelectedRow();
                kol = tabel.getSelectedColumn();

                dataterpilih = tabel.getValueAt(bar, 0).toString();
                System.out.println(dataterpilih);
                if (dataterpilih != null) {
                    btnEdit.setEnabled(true);
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
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    if(dataterpilih != null){
                        String dataEditNim = tabel.getValueAt(bar,0).toString();
                        String dataEditNama = tabel.getValueAt(bar,1).toString();
                        String dataEditAlamat = tabel.getValueAt(bar,2).toString();

                        System.out.println("data edit terpilih :"+dataEditNim+" "+dataEditNama+" "+dataEditAlamat+"");
                        viewEditForm = new ViewEditForm();

                        dispose();
                        viewEditForm.tfnim.setText(dataEditNim);
                        viewEditForm.tfnama.setText(dataEditNama);
                        viewEditForm.tfalamat.setText(dataEditAlamat);
                        viewEditForm.tfnim.setEditable(false);
                        viewEditForm.btnKembali.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setVisible(false);
                                viewEditForm.dispose();
                                new EditDataMahasiswa();
                            }
                        });
                        viewEditForm.btnEdit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try{
                                    Class.forName("com.mysql.jdbc.Driver");
                                    koneksi = DriverManager.getConnection(DBurl,
                                            DBusername, DBpassword);
                                    statement = koneksi.createStatement();
                                    nim=viewEditForm.getTfnim();
                                    nama=viewEditForm.getTfnama();
                                    alamat=viewEditForm.getTfalamat();
                                    String query = "UPDATE `mahasiswa` SET `nim`='"+nim+"',`nama`='"+nama+"',`alamat`='"+alamat+"' WHERE `nim` = '"+nim+"'";
                                    statement.executeUpdate(query);

                                    JOptionPane.showMessageDialog(null, "Edit Data Sukses!");
                                    statement.close();
                                    koneksi.close();
                                }catch(Exception ex){
                                    System.out.println(ex.getMessage());
                                }
                                viewEditForm.setVisible(false);
                                new Menu();
                            }
                        });
                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                    System.out.println("Edit Gagal!");
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

}

