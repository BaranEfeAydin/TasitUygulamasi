package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.*;

public class SeyahatPlanlayici {
    private SeyahatBilgisi seyahatBilgisi;
    private SeyahatServisi seyahatServisi;
    private JTextArea rotaTextArea;

    public SeyahatPlanlayici() {
        this.seyahatBilgisi = new SeyahatBilgisi();
        this.seyahatServisi = new SeyahatServisi();
    }

    public void baslat(Kent kent) {
        Locale.setDefault(new Locale("en", "GB"));

        JFrame frame = new JFrame("Seyahat Planlayıcı");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setSize(1200, 1200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel mevcutKonumLabel = new JLabel("Mevcut Konum:");
        mevcutKonumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField mevcutLatField = new JTextField("Lat", 5);
        JTextField mevcutLonField = new JTextField("Lon", 5);
        addPlaceholderEffect(mevcutLatField, "Lat");
        addPlaceholderEffect(mevcutLonField, "Lon");
        JPanel mevcutKonumPanel = createCompactPanel(mevcutLatField, mevcutLonField);
        mevcutKonumPanel.add(createInputField(mevcutLatField, 100, 25));
        mevcutKonumPanel.add(createInputField(mevcutLonField, 100, 25));

        JLabel hedefKonumLabel = new JLabel("Hedef Konum:");
        hedefKonumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField hedefLatField = new JTextField("Lat", 5);
        JTextField hedefLonField = new JTextField("Lon", 5);
        addPlaceholderEffect(hedefLatField, "Lat");
        addPlaceholderEffect(hedefLonField, "Lon");
        JPanel hedefKonumPanel = createCompactPanel(hedefLatField, hedefLonField);
        hedefKonumPanel.add(createInputField(hedefLatField, 100, 25));
        hedefKonumPanel.add(createInputField(hedefLonField, 100, 25));

        JLabel yolcuTipiLabel = new JLabel("Yolcu Tipi:");
        yolcuTipiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComboBox<String> yolcuTipiBox = new JComboBox<>(new String[]{"Öğrenci", "Genel", "Yaşlı"});
        JPanel yolcuTipiPanel = createCompactPanel(yolcuTipiBox);

        JLabel odemeLabel = new JLabel("Ödeme Yöntemi:");
        odemeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComboBox<String> odemeBox = new JComboBox<>(new String[]{"Nakit", "Kredi Kartı", "Kent Kart"});
        JPanel odemePanel = createCompactPanel(odemeBox);

        JLabel bakiyeLabel = new JLabel("Bakiye:");
        bakiyeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField bakiyeField = new JTextField(10);
        JPanel bakiyePanel = createCompactPanel(bakiyeField);

        JButton hesaplaButton = new JButton("Hesapla");
        hesaplaButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(mevcutKonumLabel);
        panel.add(mevcutKonumPanel);
        panel.add(hedefKonumLabel);
        panel.add(hedefKonumPanel);
        panel.add(yolcuTipiLabel);
        panel.add(yolcuTipiPanel);
        panel.add(odemeLabel);
        panel.add(odemePanel);
        panel.add(bakiyeLabel);
        panel.add(bakiyePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Dikey boşluk
        panel.add(hesaplaButton);

        ImageIcon orijinalIcon = new ImageIcon(getClass().getClassLoader().getResource("map_placeholder.png"));

        Image orijinalImage = orijinalIcon.getImage();

        JPanel haritaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int imgWidth = orijinalImage.getWidth(null);
                int imgHeight = orijinalImage.getHeight(null);

                double scaleX = (double) panelWidth / imgWidth;
                double scaleY = (double) panelHeight / imgHeight;
                double scale = Math.min(scaleX, scaleY);

                int newWidth = (int) (imgWidth * scale);
                int newHeight = (int) (imgHeight * scale);

                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;

                g.drawImage(orijinalImage, x, y, newWidth, newHeight, this);
            }
        };

        haritaPanel.setPreferredSize(new Dimension(800, 600));

        rotaTextArea = new JTextArea(10, 50);
        rotaTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(rotaTextArea);

        hesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    seyahatBilgisi.setMevcutKonum(new Konum(Double.parseDouble(mevcutLatField.getText()), Double.parseDouble(mevcutLonField.getText())));
                    seyahatBilgisi.setHedefKonum(new Konum(Double.parseDouble(hedefLatField.getText()), Double.parseDouble(hedefLonField.getText())));
                    seyahatBilgisi.setYolcu(YolcuFactory.yolcuOlustur((String) yolcuTipiBox.getSelectedItem()));
                    seyahatBilgisi.setOdeme(OdemeFactory.odemeOlustur((String) odemeBox.getSelectedItem(), Double.parseDouble(bakiyeField.getText())));
                    SeyahatHesaplayici hesaplayici = new SeyahatHesaplayici(seyahatServisi);
                    String sonuc = hesaplayici.hesapla(seyahatBilgisi, kent);
                    rotaTextArea.setText(sonuc);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli koordinat değerleri girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mevcutLatField.setBackground(Color.WHITE);
        mevcutLonField.setBackground(Color.WHITE);
        hedefLatField.setBackground(Color.WHITE);
        hedefLonField.setBackground(Color.WHITE);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(scrollPane);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.WEST);
        frame.add(haritaPanel, BorderLayout.CENTER);
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

    private JPanel createCompactPanel(JComponent... components) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        panel.setPreferredSize(new Dimension(220, 35));
        panel.setMaximumSize(new Dimension(220, 35));
        panel.setMinimumSize(new Dimension(220, 35));
        for (JComponent comp : components) {
            panel.add(comp);
        }
        return panel;
    }

    private JPanel createInputField(JTextField field, int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        field.setMaximumSize(new Dimension(width, height));
        field.setMinimumSize(new Dimension(width, height));
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(field);
        return panel;
    }

}
