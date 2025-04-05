package org.example;

import java.util.List;

public class EnYakinDurak {
    private static double minMesafe = Double.MAX_VALUE;

    public static Durak enYakinDuragiBul(double lat, double lon, List<Durak> duraklar) {
        Durak enYakinDurak = null;
        minMesafe = Double.MAX_VALUE;

        for (Durak durak : duraklar) {
            double mesafe = MesafeHesabi.haversineFormulu(lat, lon, durak.getLat(), durak.getLon());
            if (mesafe < minMesafe) {
                minMesafe = mesafe;
                enYakinDurak = durak;
            }
        }
        return enYakinDurak;
    }

    public static double getMinMesafe() {
        return minMesafe;
    }
}

