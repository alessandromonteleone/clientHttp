package it.unimore.dipi.iot.http.api.client.location.model.response.areaCircle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionList;

public class GetSubscriptionsAreaCircleResponseDescriptor {
    @SerializedName("notificationSubscriptionList")
    @Expose
    private NotificationSubscriptionList notificationSubscriptionList;

    public NotificationSubscriptionList getNotificationSubscriptionList() {
        return notificationSubscriptionList;
    }

    public void setNotificationSubscriptionList(NotificationSubscriptionList notificationSubscriptionList) {
        this.notificationSubscriptionList = notificationSubscriptionList;
    }
}
