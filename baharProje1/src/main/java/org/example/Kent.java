package org.example;

import java.util.List;

public class Kent {
    private String city;
    private Taksi taxi;
    private List<Durak> duraklar;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTaxi(Taksi taxi) {
        this.taxi = taxi;
    }

    public void setDuraklar(List<Durak> duraklar) {
        this.duraklar = duraklar;
    }

    public List<Durak> getDuraklar() {
        return duraklar;
    }

    public Taksi getTaxi() {
        return taxi;
    }

}
