package org.example;

public class Nakit extends Odeme {
    private static final double SABIT_INDIRIM_ORANI = 0.0;

    public Nakit(double bakiye) {
        super(bakiye, SABIT_INDIRIM_ORANI);
    }
}
