package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationSubscriptionListPeriodic {

    @SerializedName("periodicNotificationSubscription")
    @Expose
    private List<PeriodicNotificationSubscription> periodicNotificationSubscriptionList;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    public List<PeriodicNotificationSubscription> getPeriodicNotificationSubscriptionList() {
        return periodicNotificationSubscriptionList;
    }

    public void setPeriodicNotificationSubscriptionListList(List<PeriodicNotificationSubscription> periodicNotificationSubscriptionList) {
        this.periodicNotificationSubscriptionList = periodicNotificationSubscriptionList;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }
}
