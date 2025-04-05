package org.example;

public class KentKart extends Odeme {
    private static final double SABIT_INDIRIM_ORANI = 0.25;

    public KentKart(double bakiye) {
        super(bakiye, SABIT_INDIRIM_ORANI);
    }
}
