package org.example;

public class MesafeHesabi {
    private static final double dunyaYaricapi = 6371.0;

    public static double haversineFormulu(double lat1, double lon1, double lat2, double lon2) {
        double Lat = Math.toRadians(lat2 - lat1);
        double Lon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(Lat / 2) * Math.sin(Lat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(Lon / 2) * Math.sin(Lon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return dunyaYaricapi * c;
    }
}
