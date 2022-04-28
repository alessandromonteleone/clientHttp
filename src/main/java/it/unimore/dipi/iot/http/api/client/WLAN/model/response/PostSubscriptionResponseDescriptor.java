package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class PostSubscriptionResponseDescriptor {

    @SerializedName("_links")
    @Expose
    private Links _links;

    @SerializedName("apId")
    @Expose
    private ApId apId;

    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;

    @SerializedName("notificationEvent")
    @Expose
    private NotificationEvent notificationEvent;

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;

    public Links getLinks() {
        return _links;
    }

    public void setLinks(Links _links) {
        this._links = _links;
    }

    public ApId getApId() {
        return apId;
    }

    public void setApId(ApId apId) {
        this.apId = apId;
    }

    public String getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(String callbackReference) {
        this.callbackReference = callbackReference;
    }

    public NotificationEvent getNotificationEvent() {
        return notificationEvent;
    }

    public void setNotificationEvent(NotificationEvent notificationEvent) {
        this.notificationEvent = notificationEvent;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
