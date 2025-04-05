package org.example;

public class YolcuFactory {
    public static Yolcu yolcuOlustur(String yolcuTipi) {
        switch (yolcuTipi) {
            case "Öğrenci":
                return new Ogrenci();
            case "Genel":
                return new Genel();
            case "Yaşlı":
                return new Yasli();
            default:
                throw new IllegalArgumentException("Geçersiz yolcu tipi: " + yolcuTipi);
        }
    }
}

