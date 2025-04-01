package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class SeyahatPlanlayici {
    private double mevcutLat;
    private double mevcutLon;
    private double hedefLat;
    private double hedefLon;
    private String baslangicZamani;
    private String ulasimModu;

    public void baslat() {
        JFrame frame = new JFrame("Seyahat Planlayıcı");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel mevcutKonumLabel = new JLabel("Mevcut Konum:");
        JPanel mevcutKonumPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JTextField mevcutLatField = new JTextField("Lat");
        JTextField mevcutLonField = new JTextField("Lon");
        addPlaceholderEffect(mevcutLatField, "Lat");
        addPlaceholderEffect(mevcutLonField, "Lon");
        mevcutLatField.setPreferredSize(new Dimension(60, 20));
        mevcutLonField.setPreferredSize(new Dimension(60, 20));
        mevcutKonumPanel.add(mevcutLatField);
        mevcutKonumPanel.add(mevcutLonField);

        JLabel hedefKonumLabel = new JLabel("Hedef Konum:");
        JPanel hedefKonumPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JTextField hedefLatField = new JTextField("Lat");
        JTextField hedefLonField = new JTextField("Lon");
        addPlaceholderEffect(hedefLatField, "Lat");
        addPlaceholderEffect(hedefLonField, "Lon");
        hedefLatField.setPreferredSize(new Dimension(60, 20));
        hedefLonField.setPreferredSize(new Dimension(60, 20));
        hedefKonumPanel.add(hedefLatField);
        hedefKonumPanel.add(hedefLonField);

        JLabel zamanLabel = new JLabel("Başlangıç Zamanı:");
        JTextField zamanField = new JTextField();
        JLabel ulasimLabel = new JLabel("Ulaşım Modu:");
        String[] ulasimModlari = {"Otobüs", "Taksi", "Metro"};
        JComboBox<String> ulasimBox = new JComboBox<>(ulasimModlari);
        JButton hesaplaButton = new JButton("Hesapla");

        panel.add(mevcutKonumLabel);
        panel.add(mevcutKonumPanel);
        panel.add(hedefKonumLabel);
        panel.add(hedefKonumPanel);
        panel.add(zamanLabel);
        panel.add(zamanField);
        panel.add(ulasimLabel);
        panel.add(ulasimBox);
        panel.add(new JLabel());
        panel.add(hesaplaButton);

        hesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mevcutLat = Double.parseDouble(mevcutLatField.getText());
                    mevcutLon = Double.parseDouble(mevcutLonField.getText());
                    hedefLat = Double.parseDouble(hedefLatField.getText());
                    hedefLon = Double.parseDouble(hedefLonField.getText());
                    baslangicZamani = zamanField.getText();
                    ulasimModu = (String) ulasimBox.getSelectedItem();

                    System.out.println("Mevcut Konum: " + mevcutLat + ", " + mevcutLon);
                    System.out.println("Hedef Konum: " + hedefLat + ", " + hedefLon);
                    System.out.println("Başlangıç Zamanı: " + baslangicZamani);
                    System.out.println("Ulaşım Modu: " + ulasimModu);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli koordinat değerleri girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addPlaceholderEffect(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }
}
