package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserTrackingSubscription {
    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;

    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("userEventCriteria")
    @Expose
    private List<String> userEventCriteria;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CallbackReference getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(CallbackReference callbackReference) {
        this.callbackReference = callbackReference;
    }

    public String getClientCorrelator() {
        return clientCorrelator;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<String> getUserEventCriteria() {
        return userEventCriteria;
    }

    public void setUserEventCriteria(List<String> userEventCriteria) {
        this.userEventCriteria = userEventCriteria;
    }
}
