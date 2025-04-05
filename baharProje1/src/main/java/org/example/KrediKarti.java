package org.example;

public class KrediKarti extends Odeme {
    private static final double SABIT_INDIRIM_ORANI = 0.0;

    public KrediKarti(double bakiye) {
        super(bakiye, SABIT_INDIRIM_ORANI);
    }
}
