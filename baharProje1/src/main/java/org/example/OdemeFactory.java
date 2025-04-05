package org.example;

public class OdemeFactory {
    public static Odeme odemeOlustur(String odemeTipi,double bakiye) {
        switch (odemeTipi) {
            case "Nakit":
                return new Nakit(bakiye);
            case "Kredi Kartı":
                return new KrediKarti(bakiye);
            case "Kent Kart":
                return new KentKart(bakiye);
            default:
                throw new IllegalArgumentException("Geçersiz ödeme tipi: " + odemeTipi);
        }
    }
}
