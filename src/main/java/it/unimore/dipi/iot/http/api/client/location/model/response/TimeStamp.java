package it.unimore.dipi.iot.http.api.client.location.model.response;

public class TimeStamp {
    private double nanoSeconds;
    private double seconds;

    public double getNanoSeconds() {
        return nanoSeconds;
    }

    public void setNanoSeconds(double nanoSeconds) {
        this.nanoSeconds = nanoSeconds;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
