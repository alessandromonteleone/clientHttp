package it.unimore.dipi.iot.http.api.client.appFca.model;

public class Timestamp {

    private double seconds;
    private double nanoseconds;

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public double getNanoseconds() {
        return nanoseconds;
    }

    public void setNanoseconds(double nanoseconds) {
        this.nanoseconds = nanoseconds;
    }
}