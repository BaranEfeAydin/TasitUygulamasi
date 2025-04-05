package org.example;

import java.util.List;

public class SeyahatBilgisi {
    private Konum mevcutKonum;
    private Konum hedefKonum;
    private Yolcu yolcu;
    private Odeme odeme;
    private Durak enYakinBaslangicDuragi;
    private Durak enYakinHedefDuragi;
    private double baslangicMinMesafe;
    private double hedefMinMesafe;
    private double taksiUcreti;
    private List<RotaBilgisi> yollar;
    private double kontrol;

    public double getKontrol() {
        return kontrol;
    }

    public void setKontrol(double kontrol) {
        this.kontrol = kontrol;
    }

    public List<RotaBilgisi> getYollar() {
        return yollar;
    }

    public void setYollar(List<RotaBilgisi> yollar) {
        this.yollar = yollar;
    }

    public double getTaksiUcreti() {
        return taksiUcreti;
    }

    public void setTaksiUcreti(double taksiUcreti) {
        this.taksiUcreti = taksiUcreti;
    }

    public double getBaslangicMinMesafe() {
        return baslangicMinMesafe;
    }

    public void setBaslangicMinMesafe(double baslangicMinMesafe) {
        this.baslangicMinMesafe = baslangicMinMesafe;
    }

    public double getHedefMinMesafe() {
        return hedefMinMesafe;
    }

    public void setHedefMinMesafe(double hedefMinMesafe) {
        this.hedefMinMesafe = hedefMinMesafe;
    }

    public Durak getEnYakinBaslangicDuragi() {
        return enYakinBaslangicDuragi;
    }

    public void setEnYakinBaslangicDuragi(Durak enYakinBaslangicDuragi) {
        this.enYakinBaslangicDuragi = enYakinBaslangicDuragi;
    }

    public Durak getEnYakinHedefDuragi() {
        return enYakinHedefDuragi;
    }

    public void setEnYakinHedefDuragi(Durak enYakinHedefDuragi) {
        this.enYakinHedefDuragi = enYakinHedefDuragi;
    }

    public Konum getMevcutKonum() {
        return mevcutKonum;
    }

    public void setMevcutKonum(Konum mevcutKonum) {
        this.mevcutKonum = mevcutKonum;
    }

    public Konum getHedefKonum() {
        return hedefKonum;
    }

    public void setHedefKonum(Konum hedefKonum) {
        this.hedefKonum = hedefKonum;
    }

    public Yolcu getYolcu() {
        return yolcu;
    }

    public void setYolcu(Yolcu yolcu) {
        this.yolcu = yolcu;
    }

    public Odeme getOdeme() {
        return odeme;
    }

    public void setOdeme(Odeme odeme) {
        this.odeme = odeme;
    }

}
