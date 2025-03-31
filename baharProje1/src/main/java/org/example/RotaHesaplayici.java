package org.example;

import java.util.*;

public class RotaHesaplayici {
    public static List<RotaBilgisi> tumYollariBul(String baslangicId, String hedefId, List<Durak> duraklar) {
        List<RotaBilgisi> tumYollar = new ArrayList<>();
        List<String> mevcutYol = new ArrayList<>();
        List<GecisBilgisi> gecisler = new ArrayList<>();
        Set<String> ziyaretEdilen = new HashSet<>();

        dfs(baslangicId, hedefId, duraklar, mevcutYol, gecisler, tumYollar, ziyaretEdilen);

        return tumYollar;
    }

    private static void dfs(
            String mevcutDurak,
            String hedefId,
            List<Durak> duraklar,
            List<String> mevcutYol,
            List<GecisBilgisi> gecisler,
            List<RotaBilgisi> tumYollar,
            Set<String> ziyaretEdilen) {

        mevcutYol.add(mevcutDurak);
        ziyaretEdilen.add(mevcutDurak);

        if (mevcutDurak.equals(hedefId)) {
            tumYollar.add(new RotaBilgisi(new ArrayList<>(mevcutYol), new ArrayList<>(gecisler)));
        } else {
            Durak durak = durakBul(mevcutDurak, duraklar);
            if (durak != null) {
                // 🚏 **Bütün NextStop bağlantılarını kontrol et**
                for (NextStop baglanti : durak.getNextStops()) {
                    if (!ziyaretEdilen.contains(baglanti.getStopId())) {
                        gecisler.add(new GecisBilgisi(mevcutDurak, baglanti.getStopId(),
                                baglanti.getUcret(), baglanti.getMesafe(), baglanti.getSure()));

                        dfs(baglanti.getStopId(), hedefId, duraklar, mevcutYol, gecisler, tumYollar, ziyaretEdilen);

                        gecisler.remove(gecisler.size() - 1);
                    }
                }

                // 🔄 **TRANSFER VARSA ONU DA KONTROL ET**
                if (durak.getTransfer() != null) {
                    String transferId = durak.getTransfer().getTransferStopId();
                    if (!ziyaretEdilen.contains(transferId)) {
                        // **Geçiş bilgisine "transfer" ekleyelim**
                        gecisler.add(new GecisBilgisi(mevcutDurak, transferId,
                                durak.getTransfer().getTransferUcret(), 0, durak.getTransfer().getTransferSure())); // Transfer ücreti ve süresi manuel eklendi

                        dfs(transferId, hedefId, duraklar, mevcutYol, gecisler, tumYollar, ziyaretEdilen);

                        gecisler.remove(gecisler.size() - 1);
                    }
                }
            }
        }

        mevcutYol.remove(mevcutYol.size() - 1);
        ziyaretEdilen.remove(mevcutDurak);
    }

    private static Durak durakBul(String stopId, List<Durak> duraklar) {
        for (Durak d : duraklar) {
            if (d.getId().equals(stopId)) {
                return d;
            }
        }
        return null;
    }
}



