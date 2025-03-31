package org.example;
import java.util.List;
import java.util.Map;

public class Kent {
    private String city;
    private Taksi taxi;
    private List<Durak> duraklar;

    public String getCity() {
        return city;
    }

    public List<Durak> getDuraklar() {
        return duraklar;
    }

    public void setDuraklar(List<Durak> duraklar) {
        this.duraklar = duraklar;
    }

    public void setCity(String kent) {
        this.city = kent;
    }

    public Taksi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taksi taxi) {
        this.taxi = taxi;
    }


}
