package it.unimore.dipi.iot.http.api.client.WLAN.model.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.ApId;

public class PostSubscriptionRequestDescriptor {

    @SerializedName("_links")
    @Expose
    private Links links;

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
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
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
