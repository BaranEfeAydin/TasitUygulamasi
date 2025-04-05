package org.example;

public class Transfer {
    private String transferStopId;
    private int transferSure;
    private double transferUcret;

    public void setTransferStopId(String transferStopId) {
        this.transferStopId = transferStopId;
    }

    public void setTransferSure(int transferSure) {
        this.transferSure = transferSure;
    }

    public void setTransferUcret(double transferUcret) {
        this.transferUcret = transferUcret;
    }

    public String getTransferStopId() {
        return transferStopId;
    }

    public int getTransferSure() {
        return transferSure;
    }

    public double getTransferUcret() {
        return transferUcret;
    }

}
