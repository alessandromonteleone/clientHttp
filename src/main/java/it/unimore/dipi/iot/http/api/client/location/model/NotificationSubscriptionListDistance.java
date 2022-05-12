package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationSubscriptionListDistance {
    @SerializedName("distanceNotificationSubscription")
    @Expose
    private List<DistanceNotificationSubscription> distanceNotificationSubscriptionList;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    public List<DistanceNotificationSubscription> getDistanceNotificationSubscriptionList() {
        return distanceNotificationSubscriptionList;
    }

    public void setDistanceNotificationSubscriptionList(List<DistanceNotificationSubscription> distanceNotificationSubscriptionList) {
        this.distanceNotificationSubscriptionList = distanceNotificationSubscriptionList;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }
}
