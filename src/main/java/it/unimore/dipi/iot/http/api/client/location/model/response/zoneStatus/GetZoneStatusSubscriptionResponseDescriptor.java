package it.unimore.dipi.iot.http.api.client.location.model.response.zoneStatus;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListZoneStatus;

@Generated("jsonschema2pojo")
public class GetZoneStatusSubscriptionResponseDescriptor {

    @SerializedName("notificationSubscriptionList")
    @Expose
    private NotificationSubscriptionListZoneStatus notificationSubscriptionList;

    public NotificationSubscriptionListZoneStatus getNotificationSubscriptionList() {
        return notificationSubscriptionList;
    }

    public void setNotificationSubscriptionList(NotificationSubscriptionListZoneStatus notificationSubscriptionList) {
        this.notificationSubscriptionList = notificationSubscriptionList;
    }

}

