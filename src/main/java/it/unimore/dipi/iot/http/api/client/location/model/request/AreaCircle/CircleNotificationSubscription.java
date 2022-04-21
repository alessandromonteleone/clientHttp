package it.unimore.dipi.iot.http.api.client.location.model.request.AreaCircle;

import java.util.ArrayList;
import java.util.List;

public class CircleNotificationSubscription {
    private String clientCorrelator;
    private CallbackReference callbackReference;
    private List<String> address = new ArrayList<String>();
    private Boolean checkImmediate;
    private String enteringLeavingCriteria;
    private Integer frequency;
    private Double latitude;
    private Double longitude;
    private Integer radius;
    private Integer trackingAccuracy;

    public String getClientCorrelator() {
        return clientCorrelator;
    }
    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }
    public CallbackReference getCallbackReference() {
        return callbackReference;
    }
    public void setCallbackReference(CallbackReference callbackReference) {
        this.callbackReference = callbackReference;
    }
    public List<String> getAddress() {
        return address;
    }
    public void setAddress(List<String> address) {
        this.address = address;
    }
    public Boolean getCheckImmediate() {
        return checkImmediate;
    }
    public void setCheckImmediate(Boolean checkImmediate) {
        this.checkImmediate = checkImmediate;
    }
    public String getEnteringLeavingCriteria() {
        return enteringLeavingCriteria;
    }
    public void setEnteringLeavingCriteria(String enteringLeavingCriteria) {
        this.enteringLeavingCriteria = enteringLeavingCriteria;
    }
    public Integer getFrequency() {
        return frequency;
    }
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Integer getRadius() {
        return radius;
    }
    public void setRadius(Integer radius) {
        this.radius = radius;
    }
    public Integer getTrackingAccuracy() {
        return trackingAccuracy;
    }
    public void setTrackingAccuracy(Integer trackingAccuracy) {
        this.trackingAccuracy = trackingAccuracy;
    }
}
