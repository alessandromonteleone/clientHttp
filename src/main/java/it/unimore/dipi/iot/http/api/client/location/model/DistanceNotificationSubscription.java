package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistanceNotificationSubscription {

    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;

    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;

    @SerializedName("monitoredAddress")
    @Expose
    private List<String> monitoredAddress;

    @SerializedName("checkImmediate")
    @Expose
    private boolean checkImmediate;

    @SerializedName("criteria")
    @Expose
    private String criteria;

    @SerializedName("distance")
    @Expose
    private int distance;

    @SerializedName("frequency")
    @Expose
    private int frequency;

    @SerializedName("referenceAddress")
    @Expose
    private List<String> referenceAddress;

    @SerializedName("trackingAccuracy")
    @Expose
    private int trackingAccuracy;

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

    public List<String> getMonitoredAddress() {
        return monitoredAddress;
    }

    public void setMonitoredAddress(List<String> monitoredAddress) {
        this.monitoredAddress = monitoredAddress;
    }

    public boolean isCheckImmediate() {
        return checkImmediate;
    }

    public void setCheckImmediate(boolean checkImmediate) {
        this.checkImmediate = checkImmediate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<String> getReferenceAddress() {
        return referenceAddress;
    }

    public void setReferenceAddress(List<String> referenceAddress) {
        this.referenceAddress = referenceAddress;
    }

    public int getTrackingAccuracy() {
        return trackingAccuracy;
    }

    public void setTrackingAccuracy(int trackingAccuracy) {
        this.trackingAccuracy = trackingAccuracy;
    }
}
