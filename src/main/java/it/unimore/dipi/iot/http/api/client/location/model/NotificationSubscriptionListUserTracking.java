package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationSubscriptionListUserTracking {

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("userTrackingSubscription")
    @Expose
    private List<UserTrackingSubscription> userTrackingSubscriptionList;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<UserTrackingSubscription> getUserTrackingSubscriptionList() {
        return userTrackingSubscriptionList;
    }

    public void setUserTrackingSubscriptionList(List<UserTrackingSubscription> userTrackingSubscriptionList) {
        this.userTrackingSubscriptionList = userTrackingSubscriptionList;
    }
}
