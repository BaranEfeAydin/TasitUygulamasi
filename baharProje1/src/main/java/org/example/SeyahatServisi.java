package org.example;

import java.util.List;

public class SeyahatServisi {

    public Durak enYakinDuragiBulma(Konum konum, List<Durak> duraklar) {
        return EnYakinDurak.enYakinDuragiBul(konum.getLat(), konum.getLon(), duraklar);
    }

    public double taksiUcretiHesaplama(double mesafe, Kent kent, double mesafeh) {
        final double MIN_TAKSI_MESAFE = 3.0;
        double toplamTaksiUcreti = 0.0;

        if (mesafe > MIN_TAKSI_MESAFE) {
            int mesafeInt = (int) mesafe;
            toplamTaksiUcreti += kent.getTaxi().getOpeningFee() + (mesafeInt * kent.getTaxi().getCostPerKm());
        }

        if (mesafeh > MIN_TAKSI_MESAFE) {
            int mesafehInt = (int) mesafeh;
            toplamTaksiUcreti += kent.getTaxi().getOpeningFee() + (mesafehInt * kent.getTaxi().getCostPerKm());
        }

        return toplamTaksiUcreti;
    }

    public double toplamUcretHesabi(SeyahatBilgisi seyahatBilgisi, RotaBilgisi rota, double taksiUcreti) {
        return UcretHesabi.hesapla(
                seyahatBilgisi.getOdeme().getIndirimOrani(),
                seyahatBilgisi.getYolcu().getIndirimOrani(),
                rota.getToplamUcret()
        ) + taksiUcreti;
    }

    public double kalanBakiyeHesabi(double ucret, Odeme odeme) {
        double kalanBakiye = odeme.odemeYap(ucret);
        return (kalanBakiye >= 0.0) ? kalanBakiye : -1;
    }

    public List<RotaBilgisi> yolHesabi(SeyahatBilgisi seyahatBilgisi, Kent kent) {
        return RotaHesaplayici.tumYollariBul(
                seyahatBilgisi.getEnYakinBaslangicDuragi().getId(),
                seyahatBilgisi.getEnYakinHedefDuragi().getId(),
                kent.getDuraklar()
        );
    }
}

