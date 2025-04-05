package org.example;

public class GecisBilgisi {
    private String baslangic;
    private String hedef;
    private double ucret;
    private double mesafe;
    private double sure;

    public GecisBilgisi(String baslangic, String hedef, double ucret, double mesafe, double sure) {
        this.baslangic = baslangic;
        this.hedef = hedef;
        this.ucret = ucret;
        this.mesafe = mesafe;
        this.sure = sure;
    }

    public String getBaslangic() {
        return baslangic;
    }

    public String getHedef() {
        return hedef;
    }

    public double getUcret() {
        return ucret;
    }

    public double getMesafe() {
        return mesafe;
    }

    public double getSure() {
        return sure;
    }
}

