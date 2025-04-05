package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Locale;

public class Veri {
    private static Kent kent;

    public static Kent veriCekme() {
        Locale.setDefault(new Locale("en", "GB"));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Veri.class.getClassLoader().getResourceAsStream("veriseti.json");
            kent = objectMapper.readValue(inputStream, Kent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kent;
    }
}

