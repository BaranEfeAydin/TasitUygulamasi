package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Ogrenci ö1 = new Ogrenci();
        Yasli y1 = new Yasli();
        Genel g1 = new Genel();


        Locale.setDefault(new Locale("en", "GB"));

        Kent kent = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kent = objectMapper.readValue(new File("C:\\Users\\vıctus\\Desktop\\veriseti.json"), Kent.class);

//            for (Durak durak : kent.getDuraklar()) {
//                System.out.println("Durak: " + durak.getName() + " (" + durak.getType() + ")");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("durak konumu:" + kent.getDuraklar().get(2).getLat());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen bulunduğunuz konumun enlemini giriniz:");
        double lat = 40.80;
        System.out.println("Lütfen bulunduğunuz konumun boylamını giriniz:");
        double lon = 29.94620;
        Konum konum = new Konum(lat, lon);

        System.out.println("Lütfen gitmek istediğiniz konumun enlemini giriniz:");
        double lat1 = 40.8210;
        System.out.println("Lütfen gitmek istediğiniz konumun boylamını giriniz:");
        double lon1 = 29.9184;

        Durak id = EnYakinDurak.enYakinDuragiBul(lat, lon, kent.getDuraklar());
        double idMinMesafe = EnYakinDurak.getMinMesafe();
        Durak sd = EnYakinDurak.enYakinDuragiBul(lat1, lon1, kent.getDuraklar());
        double sdMinMesafe = EnYakinDurak.getMinMesafe();

        System.out.println("Mesafeler:");
        System.out.println(idMinMesafe);
        System.out.println(sdMinMesafe);
        System.out.println("--------------------------------");
        System.out.println(id.getId());
        System.out.println(sd.getId());

        List<RotaBilgisi> yollar = RotaHesaplayici.tumYollariBul(id.getId(), sd.getId(), kent.getDuraklar());

        for (int i = 0; i < yollar.size(); i++) {
            RotaBilgisi rota = yollar.get(i);
            System.out.println("Rota " + (i + 1) + ": " + String.join(" → ", rota.getDuraklar()));
            for (GecisBilgisi g : rota.getGecisler()) {
                System.out.println(g.getBaslangic() + " → " + g.getHedef() +
                        " (Ücret: " + g.getUcret() + " TL, Mesafe: " + g.getMesafe() + " km, Süre: " + g.getSure() + " dk)");
            }
            System.out.println("Toplam Ücret: " + (rota.getToplamUcret()) * ö1.getIndirimOrani() + " TL");
            System.out.println("Toplam Ücret İndirimsiz: " + rota.getToplamUcret() + " TL");
            System.out.println("Toplam Mesafe: " + rota.getToplamMesafe() + " km");
            System.out.println("Toplam Süre: " + rota.getToplamSure() + " dk");
            System.out.println("-------------------------------------------------");
        }

        scanner.close();
    }
}