package it.unimore.dipi.iot.http.api.client.location.model.response.userTracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListUserTracking;

public class GetUserTrackingSubscriptionResponseDescriptor {

    @SerializedName("notificationSubscriptionList")
    @Expose
    private NotificationSubscriptionListUserTracking notificationSubscriptionListUserTracking;

    public NotificationSubscriptionListUserTracking getNotificationSubscriptionListUserTracking() {
        return notificationSubscriptionListUserTracking;
    }

    public void setNotificationSubscriptionListUserTracking(NotificationSubscriptionListUserTracking notificationSubscriptionListUserTracking) {
        this.notificationSubscriptionListUserTracking = notificationSubscriptionListUserTracking;
    }
}
