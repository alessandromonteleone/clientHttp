package it.unimore.dipi.iot.http.api.client.location.model.response;

public class TerminalDistance {
    //private double accuracy;
    private double distance;
    private TimeStamp timestamp;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimeStamp timestamp) {
        this.timestamp = timestamp;
    }
}
