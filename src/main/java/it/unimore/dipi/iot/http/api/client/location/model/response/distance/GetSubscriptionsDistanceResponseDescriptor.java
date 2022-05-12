package it.unimore.dipi.iot.http.api.client.location.model.response.distance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListDistance;

public class GetSubscriptionsDistanceResponseDescriptor {
    @SerializedName("notificationSubscriptionList")
    @Expose
    private NotificationSubscriptionListDistance notificationSubscriptionListDistance;

    public NotificationSubscriptionListDistance getNotificationSubscriptionListDistance() {
        return notificationSubscriptionListDistance;
    }

    public void setNotificationSubscriptionListDistance(NotificationSubscriptionListDistance notificationSubscriptionListDistance) {
        this.notificationSubscriptionListDistance = notificationSubscriptionListDistance;
    }
}
