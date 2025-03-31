package org.example;

public abstract class Odeme {
    private double bakiye;
    private double indirimOrani;

    public Odeme(double bakiye) {
        this.bakiye = bakiye;
        this.indirimOrani = 1;
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

    public void setIndirimOrani(double indirimOrani) {
        this.indirimOrani = indirimOrani;
    }

    public double odemeYap(double tutar,double yolcuIndirimOrani){
        if(bakiye>tutar){
            bakiye -=(tutar*indirimOrani*yolcuIndirimOrani);
            return bakiye;
        }
        return 0;
    }

}
