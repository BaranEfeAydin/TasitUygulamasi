package org.example;

public abstract class Odeme {
    private double bakiye;
    private double indirimOrani;

    public Odeme(double bakiye, double indirimOrani) {
        this.bakiye = bakiye;
        this.indirimOrani = indirimOrani;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    public double getIndirimOrani() {
        return indirimOrani;
    }

    public double odemeYap(double tutar) {
        if (bakiye >= tutar) {
            bakiye -= tutar;
            return bakiye;
        } else {
            System.out.println("Yetersiz bakiye!");
            return 0;
        }
    }
}
