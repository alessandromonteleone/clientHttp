package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationSubscriptionList {

    @SerializedName("circleNotificationSubscription")
    @Expose
    private List<CircleNotificationSubscription> circleNotificationSubscription;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    public List<CircleNotificationSubscription> getCircleNotificationSubscription() {
        return circleNotificationSubscription;
    }

    public void setCircleNotificationSubscription(List<CircleNotificationSubscription> circleNotificationSubscription) {
        this.circleNotificationSubscription = circleNotificationSubscription;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }
}
