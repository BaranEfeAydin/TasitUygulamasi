package org.example;

import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "GB"));

        Kent kent = Veri.veriCekme();

        SwingUtilities.invokeLater(() -> {
            new SeyahatPlanlayici().baslat(kent);
        });

    }
}