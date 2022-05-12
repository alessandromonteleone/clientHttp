package it.unimore.dipi.iot.http.api.client.location.model.response.periodic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListPeriodic;

public class GetPeriodicSubscriptionsResponseDescriptor {

    @SerializedName("notificationSubscriptionList")
    @Expose
    private NotificationSubscriptionListPeriodic notificationSubscriptionListPeriodic;

    public NotificationSubscriptionListPeriodic getNotificationSubscriptionListPeriodic() {
        return notificationSubscriptionListPeriodic;
    }

    public void setNotificationSubscriptionListPeriodic(NotificationSubscriptionListPeriodic notificationSubscriptionListPeriodic) {
        this.notificationSubscriptionListPeriodic = notificationSubscriptionListPeriodic;
    }
}
