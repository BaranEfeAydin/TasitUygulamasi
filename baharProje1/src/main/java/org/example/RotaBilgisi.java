package org.example;

import java.util.*;

public class RotaBilgisi {
    private List<String> duraklar;
    private List<GecisBilgisi> gecisler;

    public RotaBilgisi(List<String> duraklar, List<GecisBilgisi> gecisler) {
        this.duraklar = duraklar;
        this.gecisler = gecisler;
    }

    public List<String> getDuraklar() {
        return duraklar;
    }

    public List<GecisBilgisi> getGecisler() {
        return gecisler;
    }

    public double getToplamUcret() {
        return gecisler.stream().mapToDouble(GecisBilgisi::getUcret).sum();
    }

    public double getToplamMesafe() {
        return gecisler.stream().mapToDouble(GecisBilgisi::getMesafe).sum();
    }

    public double getToplamSure() {
        return gecisler.stream().mapToDouble(GecisBilgisi::getSure).sum();
    }
}

