package org.example;

public abstract class Yolcu {
    private double indirimOrani;

    public Yolcu(double indirimOrani) {
        this.indirimOrani = indirimOrani;
    }

    public double getIndirimOrani() {
        return indirimOrani;
    }

}
