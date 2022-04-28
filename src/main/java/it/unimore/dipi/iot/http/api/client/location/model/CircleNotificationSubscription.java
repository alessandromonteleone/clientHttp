package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CircleNotificationSubscription {
    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;
    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;
    @SerializedName("address")
    @Expose
    private List<String> address = new ArrayList<String>();
    @SerializedName("checkImmediate")
    @Expose
    private Boolean checkImmediate;
    @SerializedName("enteringLeavingCriteria")
    @Expose
    private String enteringLeavingCriteria;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("radius")
    @Expose
    private Integer radius;
    @SerializedName("trackingAccuracy")
    @Expose
    private Integer trackingAccuracy;
    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

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
