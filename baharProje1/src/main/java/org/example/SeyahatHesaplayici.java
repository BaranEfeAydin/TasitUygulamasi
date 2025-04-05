package org.example;

import java.util.*;

public class SeyahatHesaplayici {

    private SeyahatServisi seyahatServisi;

    public SeyahatHesaplayici(SeyahatServisi seyahatServisi) {
        this.seyahatServisi = seyahatServisi;
    }

    public String hesapla(SeyahatBilgisi seyahatBilgisi, Kent kent) {
        StringBuilder rotaBilgisi = new StringBuilder();
        Set<RotaBilgisi> yazilanlar = new HashSet<>();

        seyahatBilgisi.setEnYakinBaslangicDuragi(seyahatServisi.enYakinDuragiBulma(seyahatBilgisi.getMevcutKonum(), kent.getDuraklar()));
        seyahatBilgisi.setBaslangicMinMesafe(EnYakinDurak.getMinMesafe());
        seyahatBilgisi.setKontrol(MesafeHesabi.haversineFormulu(
                seyahatBilgisi.getMevcutKonum().getLat(),
                seyahatBilgisi.getMevcutKonum().getLon(),
                seyahatBilgisi.getHedefKonum().getLat(),
                seyahatBilgisi.getHedefKonum().getLon()
        ));

        if (seyahatBilgisi.getKontrol() <= seyahatBilgisi.getBaslangicMinMesafe()) {
            if (seyahatServisi.kalanBakiyeHesabi(seyahatServisi.taksiUcretiHesaplama(seyahatBilgisi.getKontrol(), kent, 0.0), seyahatBilgisi.getOdeme()) <= 0.0) {
                rotaBilgisi.append("Yetersiz bakiye!!!\n");
                return rotaBilgisi.toString();
            } else {
                rotaBilgisi.append("🚕 EN UYGUN ROTA:\n");
                rotaBilgisi.append("Tüm yol taksi ile gidilmiştir!\n");
                rotaBilgisi.append("Toplam Ücret: ").append(seyahatServisi.taksiUcretiHesaplama(seyahatBilgisi.getKontrol(), kent, 0.0)).append(" TL\n");
                rotaBilgisi.append("Kalan bakiye: ").append(seyahatServisi.kalanBakiyeHesabi(seyahatServisi.taksiUcretiHesaplama(seyahatBilgisi.getKontrol(), kent, 0.0), seyahatBilgisi.getOdeme())).append(" TL\n");
                return rotaBilgisi.toString();
            }
        }

        seyahatBilgisi.setEnYakinHedefDuragi(seyahatServisi.enYakinDuragiBulma(seyahatBilgisi.getHedefKonum(), kent.getDuraklar()));
        seyahatBilgisi.setHedefMinMesafe(EnYakinDurak.getMinMesafe());
        seyahatBilgisi.setTaksiUcreti(seyahatServisi.taksiUcretiHesaplama(
                seyahatBilgisi.getBaslangicMinMesafe(),
                kent,
                seyahatBilgisi.getHedefMinMesafe()
        ));

        seyahatBilgisi.setYollar(seyahatServisi.yolHesabi(seyahatBilgisi, kent));

        if (seyahatBilgisi.getYollar().isEmpty()) {
            return "Uygun rota bulunamadı.";
        }

        RotaBilgisi enUcuzRota = Collections.min(seyahatBilgisi.getYollar(),
                Comparator.comparingDouble(r -> seyahatServisi.toplamUcretHesabi(seyahatBilgisi, r, seyahatBilgisi.getTaksiUcreti())));
        RotaBilgisi enHizliRota = Collections.min(seyahatBilgisi.getYollar(),
                Comparator.comparingDouble(RotaBilgisi::getToplamSure));

        rotaBilgisi.append("🚕 EN UYGUN ROTA:\n");
        rotaBilgisi.append(rotaDetayiniYaz(enUcuzRota, seyahatBilgisi, kent));
        yazilanlar.add(enUcuzRota);

        if (!enHizliRota.equals(enUcuzRota)) {
            rotaBilgisi.append("⚡ EN HIZLI ROTA:\n");
            rotaBilgisi.append(rotaDetayiniYaz(enHizliRota, seyahatBilgisi, kent));
            yazilanlar.add(enHizliRota);
        }

        rotaBilgisi.append("📌 DİĞER ROTALAR:\n");
        for (RotaBilgisi rota : seyahatBilgisi.getYollar()) {
            if (!yazilanlar.contains(rota)) {
                rotaBilgisi.append(rotaDetayiniYaz(rota, seyahatBilgisi, kent));
            }
        }

        return rotaBilgisi.toString();
    }

    private String rotaDetayiniYaz(RotaBilgisi rota, SeyahatBilgisi seyahatBilgisi, Kent kent) {
        StringBuilder sb = new StringBuilder();
        double taksiUcreti = seyahatBilgisi.getTaksiUcreti();
        double ucret = seyahatServisi.toplamUcretHesabi(seyahatBilgisi, rota, taksiUcreti);
        double kalanBakiye = seyahatServisi.kalanBakiyeHesabi(ucret, seyahatBilgisi.getOdeme());

        if (kalanBakiye >= 0) {
            sb.append("Rota: ").append(String.join(" → ", rota.getDuraklar())).append("\n");
            for (GecisBilgisi g : rota.getGecisler()) {
                sb.append(g.getBaslangic()).append(" → ").append(g.getHedef())
                        .append(" (Ücret: ").append(g.getUcret()).append(" TL, Mesafe: ")
                        .append(g.getMesafe()).append(" km, Süre: ").append(g.getSure()).append(" dk)\n");
            }
            sb.append("Toplam Ücret: ").append(ucret).append(" TL\n");
            sb.append("Toplam Mesafe: ").append(rota.getToplamMesafe()).append(" km\n");
            sb.append("Toplam Süre: ").append(rota.getToplamSure()).append(" dk\n");
            sb.append("Kalan bakiye: ").append(kalanBakiye).append(" TL\n");
        } else {
            sb.append("Yetersiz Bakiye!!!\n");
        }
        sb.append("-------------------------------------------------\n");
        return sb.toString();
    }
}

