package it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListZonalTraffic;

public class GetZonalTrafficSubscriptionResponseDescriptor {

    @SerializedName("notificationSubscriptionList")
    @Expose
    private  NotificationSubscriptionListZonalTraffic notificationSubscriptionListZonalTraffic;

    public NotificationSubscriptionListZonalTraffic getNotificationSubscriptionListZonalTraffic() {
        return notificationSubscriptionListZonalTraffic;
    }

    public void setNotificationSubscriptionListZonalTraffic(NotificationSubscriptionListZonalTraffic notificationSubscriptionListZonalTraffic) {
        this.notificationSubscriptionListZonalTraffic = notificationSubscriptionListZonalTraffic;
    }
}
