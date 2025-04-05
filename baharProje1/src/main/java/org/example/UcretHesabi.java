package org.example;

public class UcretHesabi {
    public static double hesapla(double indirimüt, double indirimyt, double ucret) {
        return ucret * (1.0 - indirimyt) * (1.0 - indirimüt);
    }
}
