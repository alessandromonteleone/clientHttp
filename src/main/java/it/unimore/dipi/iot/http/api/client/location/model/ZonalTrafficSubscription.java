package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZonalTrafficSubscription {

    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;

    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("zoneId")
    @Expose
    private  String zoneId;

    @SerializedName("userEventCriteria")
    @Expose
    List<String> userEventCriteria = null;

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

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public List<String> getUserEventCriteria() {
        return userEventCriteria;
    }

    public void setUserEventCriteria(List<String> userEventCriteria) {
        this.userEventCriteria = userEventCriteria;
    }
}
