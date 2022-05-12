package it.unimore.dipi.iot.http.api.client.location.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class PeriodicNotificationSubscription {

    @SerializedName("address")
    @Expose
    private List<String> address = null;
    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;
    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("link")
    @Expose
    private List<Link> link = null;
    @SerializedName("requestedAccuracy")
    @Expose
    private Integer requestedAccuracy;
    @SerializedName("requester")
    @Expose
    private String requester;
    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Integer getRequestedAccuracy() {
        return requestedAccuracy;
    }

    public void setRequestedAccuracy(Integer requestedAccuracy) {
        this.requestedAccuracy = requestedAccuracy;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

}

