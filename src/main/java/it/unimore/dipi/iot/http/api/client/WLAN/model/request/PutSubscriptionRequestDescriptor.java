package it.unimore.dipi.iot.http.api.client.WLAN.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;

public class PutSubscriptionRequestDescriptor {

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;

    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;

    @SerializedName("_links")
    @Expose
    private Links _links;

    @SerializedName("apId")
    @Expose
    private ApId apId;

    @SerializedName("notificationEvent")
    @Expose
    private NotificationEvent notificationEvent;

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(String callbackReference) {
        this.callbackReference = callbackReference;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public ApId getApId() {
        return apId;
    }

    public void setApId(ApId apId) {
        this.apId = apId;
    }

    public NotificationEvent getNotificationEvent() {
        return notificationEvent;
    }

    public void setNotificationEvent(NotificationEvent notificationEvent) {
        this.notificationEvent = notificationEvent;
    }
}
