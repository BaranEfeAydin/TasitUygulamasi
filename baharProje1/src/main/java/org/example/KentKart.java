package org.example;

public class KentKart extends Odeme{
    private static final double SABIT_INDIRIM_ORANI = 0.5;
    public KentKart(double bakiye) {
        super(bakiye);
        setIndirimOrani(SABIT_INDIRIM_ORANI);
    }
}
